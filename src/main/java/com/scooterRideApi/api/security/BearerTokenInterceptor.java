package com.scooterRideApi.api.security;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.http.HttpStatus;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BearerTokenInterceptor implements org.springframework.web.servlet.HandlerInterceptor {

    private ConcurrentHashMap<String, String> tokenStore = new ConcurrentHashMap<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.replaceFirst("Bearer ", "");
            // Simulate token validation. Replace with your actual service call.
            String username = validateToken(token);
            if (username != null) {
                return true;
            }
        }
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write("Invalid or missing token");
        return false;
    }
    public String validateToken(String token) {
        return tokenStore.get(token);
    }
}
