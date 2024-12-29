package com.scooterRideApi.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scooterRideApi.api.dto.VehicleDTO;
import com.scooterRideApi.api.model.Vehicle;
import com.scooterRideApi.api.service.VehicleService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {

  @Autowired
  private VehicleService vehicleService;

  @GetMapping("/getData")
  public ResponseEntity<Vehicle> getVehicle(@RequestBody Long vehicleId) {
    Optional<Vehicle> vehicle = vehicleService.findVehicle(vehicleId);
    return ResponseEntity.ok(vehicle.orElse(null));
  }

  @PostMapping("/registerNew")
  public ResponseEntity<String> registerNewVehicle(@RequestBody VehicleDTO vehicle) {
    Vehicle newVehicle = vehicleService.registerNewVehicle(vehicle);
    return ResponseEntity.status(HttpStatus.CREATED).body("created vehicle ID: " + newVehicle.getId());
  }

  @DeleteMapping("/delete")
  public ResponseEntity<String> deleteVehicle(@RequestBody Long Id) {
    try {
      vehicleService.deleteVehicle(Id);
      return ResponseEntity.ok("deleted vehicle with ID: " + Id);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

  @PutMapping("/update")
  public ResponseEntity<String> updateVehicle(@RequestBody Vehicle vehicle) {
    try {
      Vehicle updatedVehicle = vehicleService.updateVehicle(vehicle);
      return ResponseEntity.ok("updated vehicle : " + updatedVehicle.getId());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

  @PostMapping("/startRide")
  public ResponseEntity<String> reserveVehicle(@RequestBody Long Id) {
    if (vehicleService.reserveVehicle(Id)) {

      return ResponseEntity.status(HttpStatus.OK).body("" + Id);
    } else {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("vehicle already reserved");
    }
  }

  @PostMapping("/endRide")
  public ResponseEntity<String> unReserveVehicle(@RequestBody Long Id) {
    if (vehicleService.unReserveVehicle(Id)) {
      return ResponseEntity.status(HttpStatus.OK).body("" + Id);
    } else {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("reservation cancelation failed");
    }
  }
}
