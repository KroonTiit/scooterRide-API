package com.scooterRideApi.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scooterRideApi.api.dto.RiderDTO;
import com.scooterRideApi.api.model.Rider;
import com.scooterRideApi.api.security.BearerTokenInterceptor;
import com.scooterRideApi.api.service.RiderService;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/users")
@RestController
class UserController {
    @Autowired
    private RiderService riderService;

    @Autowired
    private BearerTokenInterceptor bearerTokenInterceptor;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RiderDTO rider) {
        try {
            riderService.registerUser(rider);
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody RiderDTO rider) {
        if (riderService.authenticateUser(rider)) {
            return ResponseEntity.ok("Login successful: " + riderService.generateToken(rider.getUsername()));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    @GetMapping("/getUserData")
    public ResponseEntity<Object> getUserData(@RequestHeader("Authorization") String token) {
        String authToken = token.replaceFirst("Bearer ", "");
        String username = bearerTokenInterceptor.validateToken(authToken);
        if (username != null) {
            Optional<Rider> user = riderService.riderRepository.findByUsername(username);
            return ResponseEntity.ok(user.orElse(null));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token");
        }
    }
}
