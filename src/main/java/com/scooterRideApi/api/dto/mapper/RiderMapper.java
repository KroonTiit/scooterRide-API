package com.scooterRideApi.api.dto.mapper;

import com.scooterRideApi.api.dto.RiderDTO;
import com.scooterRideApi.api.model.Rider;

public class RiderMapper {
  public Rider mapToModel(RiderDTO dto) {
    return new Rider(dto.getUsername(), dto.getPassword(), dto.getEmail());
  }
}
