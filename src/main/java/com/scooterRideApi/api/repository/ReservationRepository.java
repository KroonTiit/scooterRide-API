package com.scooterRideApi.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scooterRideApi.api.model.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
