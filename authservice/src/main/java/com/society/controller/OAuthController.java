package com.society.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.society.entity.User;
import com.society.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.beans.Transient;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class OAuthController  {

    @Autowired
    UserRepository userRepository;

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String clientSecret;

    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    private String redirectUri;

    @Value("${spring.security.oauth2.client.provider.google.token-uri}")
    private String tokenUri;

    @Value("${spring.security.oauth2.client.provider.google.user-info-uri}")
    private String userInfoUri;

    private HttpClient httpClient;
    private ObjectMapper objectMapper;

    public OAuthController() {
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    @Transactional
    @GetMapping("/redirect")
    public void oauthCallBack(@RequestParam("code") String code,HttpServletResponse httpResponse) throws Exception {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("code", code);
        requestBody.put("client_id", clientId);
        requestBody.put("client_secret", clientSecret);
        requestBody.put("redirect_uri", redirectUri);
        requestBody.put("grant_type", "authorization_code");

        String requestBodyJson = objectMapper.writeValueAsString(requestBody);

        HttpRequest tokenRequest = HttpRequest.newBuilder()
                .uri(URI.create(this.tokenUri))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBodyJson))
                .build();

        HttpResponse<String> tokenResponse = httpClient.send(tokenRequest, HttpResponse.BodyHandlers.ofString());
        Map<String, Object> tokens = objectMapper.readValue(tokenResponse.body(), Map.class);

        String accessToken = (String) tokens.get("access_token");

        HttpRequest userInfoRequest = HttpRequest.newBuilder()
                .uri(URI.create(userInfoUri + "?access_token=" + accessToken))
                .GET()
                .build();

        HttpResponse<String> userInfoResponse = httpClient.send(userInfoRequest, HttpResponse.BodyHandlers.ofString());
        User user = objectMapper.readValue(userInfoResponse.body(), User.class);
        user.setId(UUID.randomUUID().toString());
        try {
            userRepository.findByName(user.getName());
        }catch (Exception e){
            userRepository.save(user);
        }

        httpResponse.sendRedirect("http://localhost:4200/user");
    }
}
