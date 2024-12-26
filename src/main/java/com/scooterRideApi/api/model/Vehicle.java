package com.scooterRideApi.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Vehicle {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long Id;

  private Integer stateOfCharge;

  private Float latitude;
  
  private Float longitude;

}
