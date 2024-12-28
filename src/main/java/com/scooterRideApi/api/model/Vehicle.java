package com.scooterRideApi.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Vehicle {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long Id;

  private Boolean reserved = false;

  private Reservation reservation;
  
  private Integer stateOfCharge;

  private Location location;

}
