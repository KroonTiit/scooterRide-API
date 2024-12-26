package com.scooterRideApi.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scooterRideApi.api.model.Rider;


@Repository
public interface RiderRepository extends JpaRepository<Rider, Long> {
  Optional<Rider> findByUsername(String username);
}
