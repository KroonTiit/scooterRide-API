package com.scooterRideApi.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scooterRideApi.api.model.Reservation;
import com.scooterRideApi.api.model.Vehicle;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
  Optional<Reservation> findByVehicleAndEndTimeIsNull(Vehicle vehicle);
}
