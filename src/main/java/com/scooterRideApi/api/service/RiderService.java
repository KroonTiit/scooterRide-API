package com.scooterRideApi.api.service;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scooterRideApi.api.model.Rider;
import com.scooterRideApi.api.repository.RiderRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Service
public class RiderService {
    @Autowired
    public RiderRepository riderRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private ConcurrentHashMap<String, String> tokenStore = new ConcurrentHashMap<>();

    public Rider registerUser(String username, String password, String email) {
        if (riderRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }
        Rider rider = new Rider();
        rider.setUsername(username);
        rider.setPassword(passwordEncoder.encode(password));
        rider.setEmail(email);
        return riderRepository.save(rider);
    }

    public boolean authenticateUser(String username, String password) {
        Optional<Rider> rider = riderRepository.findByUsername(username);
        return rider.isPresent() && passwordEncoder.matches(password, rider.get().getPassword());
    }

    public String generateToken(String username) {
        String token = UUID.randomUUID().toString();
        tokenStore.put(token, username);
        return token;
    }

    public String validateToken(String token) {
        return tokenStore.get(token);
    }
}