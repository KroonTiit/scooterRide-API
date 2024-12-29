package com.scooterRideApi.api.service;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scooterRideApi.api.model.Reservation;
import com.scooterRideApi.api.model.Vehicle;
import com.scooterRideApi.api.repository.ReservationRepository;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    public Reservation startReservation(Vehicle currentVehicle) {
        Reservation reservation = new Reservation();

        reservation.setStartTime(LocalDateTime.now());
        reservation.setEndTime(null);
        reservation.setStartingLocation(currentVehicle.getLocation());
        reservation.setVehicle(currentVehicle);

        return reservationRepository.save(reservation);
    }

    public Reservation endReservation(Vehicle currentVehicle) {
        //Long Id = currentVehicle.getReservations();
        Reservation reservation = reservationRepository.findByVehicleAndEndTimeIsNull(currentVehicle).get();
        //Reservation reservation = reservationRepository.findById(Id).get();

        reservation.setEndTime(LocalDateTime.now());
        reservation.setEndingLocation(currentVehicle.getLocation());
        reservation.setCost(calculateCost(reservation.getStartTime(), reservation.getEndTime()));

        return reservationRepository.save(reservation);
    }

    private Double calculateCost(LocalDateTime startTime, LocalDateTime endTime) {
        // 1€ per rental start
        Double cost = 1.0;

        // Calculate total duration in seconds
        long totalSeconds = Duration.between(startTime, endTime).getSeconds();
        long totalMinutes = totalSeconds / 60;
        long remainingSeconds = totalSeconds % 60;

        // Calculate cost for the first 10 minutes (0.5€ per minute)
        long firstTenMinutes = Math.min(totalMinutes, 10);
        cost += firstTenMinutes * 0.5;

        // Calculate cost for the remaining minutes after the first 10 minutes (0.3€ per
        // minute)
        if (totalMinutes > 10) {
            long extraMinutes = totalMinutes - 10;
            cost += extraMinutes * 0.3;
        }

        // Account for any remaining seconds beyond the last full minute as an extra
        // minute
        if (remainingSeconds > 0) {
            if (totalMinutes < 10) {
                cost += 0.5; // Charge at 0.5€ rate
            } else {
                cost += 0.3; // Charge at 0.3€ rate
            }
        }

        return cost;
    }
}
