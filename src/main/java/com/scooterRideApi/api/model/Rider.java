package com.scooterRideApi.api.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString(exclude = "password")
public class Rider {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long Id;

  @NotBlank(message = "Email is required")
  @Email(message = "Invalid email format")
  @Column(unique = true, nullable = false)
  private String email;

  private String password;

  @NotBlank(message = "Name is required")
  private String username;

  @Column(updatable = false)
  private LocalDateTime createdDtime;

  @PrePersist
  protected void onCreate() {
      createdDtime = LocalDateTime.now();
  }

  public Rider(String username, String password, String email) {
    this.username = username;
    this.password = password;
    this. email = email;

  }

}
