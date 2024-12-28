package com.scooterRideApi.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Value;

@Value
public class RiderDTO {
  @NotBlank(message = "Email is required")
  private String email;

  @NotBlank(message = "password is required")
  private String password;

  @NotBlank(message = "Name is required")
  private String username;

}
