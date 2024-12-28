package com.scooterRideApi.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scooterRideApi.api.model.Location;
import com.scooterRideApi.api.model.Vehicle;
import com.scooterRideApi.api.repository.VehicleRepository;

@Service
public class VehicleService {
  
  @Autowired
  private VehicleRepository vehicleRepository;

  @Autowired
  private ReservationService reservationService;
  
  public Optional<Vehicle> findVehicle(Long vehicleId){
    Optional<Vehicle> vehicle = vehicleRepository.findById(vehicleId);
    return vehicle;
  }

  public Vehicle registerNewVehicle(Vehicle vehicle) {
    // create a mapper here
    //coordinates are set to a static for the test application
    Location location = new Location();

    location = vehicle.getLocation();
    if (location.getId() != null){
      location.setLatitude(58.378025);
      location.setLongitude(26.728493);
    }
    
    Vehicle newVehicle = new Vehicle();

    newVehicle.setReserved(false);
    newVehicle.setLocation(location);
    newVehicle.setStateOfCharge(vehicle.getStateOfCharge());
    return vehicleRepository.save(newVehicle);
  }

  public void deleteVehicle(Long id) {
    vehicleRepository.deleteById(id);
  }

  public Vehicle updateVehicle(Vehicle vehicle) {
    Vehicle currentVehicle = vehicleRepository.findById(vehicle.getId()).get();
    currentVehicle.setLocation(vehicle.getLocation());
    currentVehicle.setStateOfCharge(vehicle.getStateOfCharge());
    return vehicleRepository.save(currentVehicle);
  }

  public Boolean reserveVehicle(Long Id){
    Vehicle currentVehicle = vehicleRepository.findById(Id).get();
    Boolean reserved = currentVehicle.getReserved();
    if (!reserved) {

      currentVehicle.setReserved(true);
      currentVehicle.setReservation(reservationService.startReservation(currentVehicle));
      vehicleRepository.save(currentVehicle);

      return true; 
    } else {
      return false;
    }
  }

  public Boolean unReserveVehicle(Long Id){
    Vehicle currentVehicle = vehicleRepository.findById(Id).get();
    Boolean reserved = currentVehicle.getReserved();
    if (!reserved) {
      currentVehicle.setReservation(reservationService.endReservation(currentVehicle));
      currentVehicle.setReserved(false);
      vehicleRepository.save(currentVehicle);
      return true; 
    } else {
      return false;
    }
  }


}
