package com.society.bean;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2LoginAuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.Map;

public class OAuthSuccess implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // Extract OAuth2 access token
        OAuth2LoginAuthenticationToken token = (OAuth2LoginAuthenticationToken) authentication;
        OAuth2User userInfo= token.getPrincipal();
        for(Map.Entry<String, Object> info:userInfo.getAttributes().entrySet()){
            System.out.println(info.getKey()+":"+info.getValue());
        }
        OAuth2AccessToken accessToken = token.getAccessToken();

        if (accessToken != null) {
            // Send token to frontend via redirect with a query param or custom header
            response.setHeader("Authorization", "Bearer " + accessToken.getTokenValue());
            response.sendRedirect("http://localhost:4200/user");  // Redirect to a user dashboard or another page
        } else {
            throw new OAuth2AuthenticationException("Access token is missing");
        }
    }
}
