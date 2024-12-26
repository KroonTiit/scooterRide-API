package com.scooterRideApi.api.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

@Entity
@Data
public class Reservation {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long Id;

  private LocalDateTime startTime;
  private LocalDateTime endTime;
  private Location startingLocation;
  private Location endingLocation;
  private Float cost;
  
}
