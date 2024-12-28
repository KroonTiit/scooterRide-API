package com.scooterRideApi.api.dto;

import com.scooterRideApi.api.model.Location;
import com.scooterRideApi.api.model.Reservation;

import lombok.Value;

@Value
public class VehicleDTO {

  private Boolean reserved;

  private Reservation reservation;

  private Integer stateOfCharge;

  private Location location;

}
