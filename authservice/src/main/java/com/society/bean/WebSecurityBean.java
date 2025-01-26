package com.society.bean;

import com.society.controller.OAuthController;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

@Configuration
@EnableWebSecurity
public class WebSecurityBean {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth->auth
                .requestMatchers("/api/auth/redirect","/api/payment").permitAll()
                .anyRequest().authenticated()
        ).oauth2Login(oauth2 ->oauth2
                .redirectionEndpoint(endpoint->endpoint
                        .baseUri("/api/auth/redirect"))
                .successHandler(new OAuthSuccess())
                .defaultSuccessUrl("http://localhost:4200/user"));

        return http.build();
    }
}
