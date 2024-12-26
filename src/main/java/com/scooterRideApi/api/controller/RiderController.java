package com.scooterRideApi.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scooterRideApi.api.model.Rider;
import com.scooterRideApi.api.service.RiderService;

@RequestMapping("/api/users")
@RestController
class UserController {
    @Autowired
    private RiderService riderService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Rider rider) {
        try {
          riderService.registerUser(rider.getUsername(), rider.getPassword(), rider.getEmail());
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Rider rider) {
        if (riderService.authenticateUser(rider.getUsername(), rider.getPassword())) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }
}
