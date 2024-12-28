package com.scooterRideApi.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Vehicle {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long Id;

  private Boolean reserved;

  private Reservation reservation;

  private Integer stateOfCharge;

  private Location location;

  public Vehicle(Boolean reserved, Reservation reservation, Integer stateOfCharge, Location location) {
    this.reserved = reserved;
    this.reservation = reservation;
    this.stateOfCharge = stateOfCharge;
    this.location = location;
  }

}
