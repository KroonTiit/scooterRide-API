package com.scooterRideApi.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scooterRideApi.api.model.Vehicle;
import com.scooterRideApi.api.service.VehicleService;


@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {

  @Autowired
  private VehicleService vehicleService;

  @GetMapping("/getData")
  private ResponseEntity<Vehicle> getVehicle(@RequestBody Long vehicleId){
    Optional<Vehicle> vehicle = vehicleService.findVehicle(vehicleId);
    return ResponseEntity.ok(vehicle.orElse(null));
  } 

  @PostMapping("/registerNew")
  private ResponseEntity<String> registerNewVehicle(@RequestBody Vehicle vehicle){
    Vehicle newVehicle = vehicleService.registerNewVehicle(vehicle);
    return ResponseEntity.status(HttpStatus.CREATED).body("created vehicle ID: "+ newVehicle.getId());
  }

  @DeleteMapping("/delete")
  private ResponseEntity<String> deleteVehicle(@RequestBody Long Id){
    try {
      vehicleService.deleteVehicle(Id);
      return ResponseEntity.ok("deleted vehicle with ID: " + Id );
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

  @PutMapping("/update")
  private ResponseEntity<String> updateVehicle(@RequestBody Vehicle vehicle){
    Vehicle updatedVehicle = vehicleService.updateVehicle(vehicle);
    return ResponseEntity.ok("updated vehicle : " + updatedVehicle.getId() );
  }

  @PostMapping("/startRide")
  private ResponseEntity<String> reserveVehicle(@RequestBody Long Id){
    if (vehicleService.reserveVehicle(Id)) {

      return ResponseEntity.status(HttpStatus.OK).body(""+Id);
    } else {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("vehicle already reserved");
    }
  } 

  @PostMapping("/endRide")
  private ResponseEntity<String> unReserveVehicle(@RequestBody Long Id){
    if (vehicleService.unReserveVehicle(Id)) {
      return ResponseEntity.status(HttpStatus.OK).body(""+Id);
    } else {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("reservation cancelation failed");
    }
  } 
}
