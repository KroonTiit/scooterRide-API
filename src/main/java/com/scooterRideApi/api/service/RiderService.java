package com.scooterRideApi.api.service;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scooterRideApi.api.dto.RiderDTO;
import com.scooterRideApi.api.dto.mapper.RiderMapper;
import com.scooterRideApi.api.model.Rider;
import com.scooterRideApi.api.repository.RiderRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Service
public class RiderService {
    @Autowired
    public RiderRepository riderRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    public Rider registerUser(RiderDTO rider) {
        if (riderRepository.findByUsername(rider.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }
        RiderMapper riderMapper = new RiderMapper();
        return riderRepository.save(riderMapper.mapToModel(rider));
    }

    public boolean authenticateUser(RiderDTO rider) {
        Optional<Rider> currentRider = riderRepository.findByUsername(rider.getUsername());
        return currentRider.isPresent() && passwordEncoder.matches(rider.getPassword(), currentRider.get().getPassword());
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