package com.scooterRideApi.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scooterRideApi.api.dto.VehicleDTO;
import com.scooterRideApi.api.dto.mapper.VehicleMapper;
import com.scooterRideApi.api.model.Location;
import com.scooterRideApi.api.model.Vehicle;
import com.scooterRideApi.api.repository.VehicleRepository;

@Service
public class VehicleService {

  @Autowired
  private VehicleRepository vehicleRepository;

  @Autowired
  private ReservationService reservationService;

  public Optional<Vehicle> findVehicle(Long vehicleId) {
    Optional<Vehicle> vehicle = vehicleRepository.findById(vehicleId);
    return vehicle;
  }

  public Vehicle registerNewVehicle(VehicleDTO vehicle) {
    // coordinates are set to a static for the test application

    Location location = vehicle.getLocation();
    if (location.getId() != null) {
      location.setLatitude(58.378025);
      location.setLongitude(26.728493);
    }
    VehicleMapper vehicleMapper = new VehicleMapper();
    return vehicleRepository.save(vehicleMapper.mapToModel(vehicle));
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

  public Boolean reserveVehicle(Long Id) {
    Vehicle currentVehicle = vehicleRepository.findById(Id).get();
    if (!currentVehicle.isReserved()) {

      currentVehicle.setReserved(true);
      reservationService.startReservation(currentVehicle);

      return true;
    } else {
      return false;
    }
  }

  public Boolean unReserveVehicle(Long Id) {
    Vehicle currentVehicle = vehicleRepository.findById(Id).get();
    if (!currentVehicle.isReserved()) {

      reservationService.endReservation(currentVehicle);
      currentVehicle.setReserved(false);

      return true;
    } else {
      return false;
    }
  }

}
