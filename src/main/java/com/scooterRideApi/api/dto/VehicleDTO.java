package com.scooterRideApi.api.dto;

import java.util.ArrayList;
import java.util.List;

import com.scooterRideApi.api.model.Location;
import com.scooterRideApi.api.model.Reservation;

import lombok.Value;

@Value
public class VehicleDTO {


  private Boolean reserved;

  private List<Reservation> reservations = new ArrayList<>();

  private Integer stateOfCharge;

  private Location location;



}
