package com.scooterRideApi.api.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Vehicle {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long Id;

  private Boolean reserved;

  @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Reservation> reservations = new ArrayList<>();

  private Integer stateOfCharge;

  @OneToOne
  private Location location;

  public Vehicle(Boolean reserved, List<Reservation> reservations, Integer stateOfCharge, Location location) {
    this.reserved = reserved;
    this.reservations = reservations;
    this.stateOfCharge = stateOfCharge;
    this.location = location;
  }

  public Boolean isReserved(){
    return reserved;
  }

}
