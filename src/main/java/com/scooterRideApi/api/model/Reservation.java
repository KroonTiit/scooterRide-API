package com.scooterRideApi.api.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Reservation {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Long Id;

  private LocalDateTime startTime;
  private LocalDateTime endTime;

  @OneToOne
  private Location startingLocation;

  @OneToOne
  private Location endingLocation;

  private Double cost;
  
  @ManyToOne
  private Vehicle vehicle;

}
