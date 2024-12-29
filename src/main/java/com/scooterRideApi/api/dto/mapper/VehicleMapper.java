package com.scooterRideApi.api.dto.mapper;

import com.scooterRideApi.api.dto.VehicleDTO;
import com.scooterRideApi.api.model.Vehicle;

import lombok.Value;

@Value
public class VehicleMapper {
  public Vehicle mapToModel(VehicleDTO dto) {
    return new Vehicle(dto.getReserved(), dto.getReservations(), dto.getStateOfCharge(), dto.getLocation());
  }
}
