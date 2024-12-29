package com.scooterRideApi.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.scooterRideApi.api.controller.VehicleController;
import com.scooterRideApi.api.dto.VehicleDTO;
import com.scooterRideApi.api.dto.mapper.VehicleMapper;
import com.scooterRideApi.api.model.Location;
import com.scooterRideApi.api.model.Vehicle;
import com.scooterRideApi.api.service.VehicleService;

public class VehicleControllerTest {
  
    @InjectMocks
    private VehicleController vehicleController;

    @Mock
    private VehicleService vehicleService;

    private VehicleDTO vehicleDTO;

    private Location location;

    private Vehicle vehicle;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        location = new Location();
        location.setLatitude(58.378025);
        location.setLongitude(26.728493);
        vehicleDTO = new VehicleDTO(false, 100, location);
        VehicleMapper vehicleMapper = new VehicleMapper();
        vehicle = vehicleMapper.mapToModel(vehicleDTO);
        vehicle.setId(1L);


    }

    @Test
    void testGetVehicle() {
        Long vehicleId = 1L;

        when(vehicleService.findVehicle(vehicleId)).thenReturn(Optional.of(vehicle));

        ResponseEntity<Vehicle> response = vehicleController.getVehicle(vehicleId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(vehicle, response.getBody());
    }

      @Test
      void testRegisterNewVehicle() {
          when(vehicleService.registerNewVehicle(vehicleDTO)).thenReturn(vehicle);

          ResponseEntity<String> response = vehicleController.registerNewVehicle(vehicleDTO);

          assertEquals(HttpStatus.CREATED, response.getStatusCode());
          assertEquals("created vehicle ID: 1", response.getBody());
      }

    @Test
    void testDeleteVehicleSuccess() {
        Long vehicleId = 1L;
        doNothing().when(vehicleService).deleteVehicle(vehicleId);

        ResponseEntity<String> response = vehicleController.deleteVehicle(vehicleId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("deleted vehicle with ID: 1", response.getBody());
    }

    @Test
    void testDeleteVehicleFailure() {
        Long vehicleId = 1L;
        doThrow(new RuntimeException("Deletion failed")).when(vehicleService).deleteVehicle(vehicleId);

        ResponseEntity<String> response = vehicleController.deleteVehicle(vehicleId);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Deletion failed", response.getBody());
    }

    @Test
    void testUpdateVehicle() {
        when(vehicleService.updateVehicle(vehicle)).thenReturn(vehicle);

        ResponseEntity<String> response = vehicleController.updateVehicle(vehicle);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("updated vehicle : 1", response.getBody());
    }

    @Test
    void testReserveVehicleSuccess() {
        Long vehicleId = 1L;
        when(vehicleService.reserveVehicle(vehicleId)).thenReturn(true);

        ResponseEntity<String> response = vehicleController.reserveVehicle(vehicleId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("1", response.getBody());
    }

    @Test
    void testReserveVehicleFailure() {
        Long vehicleId = 1L;
        when(vehicleService.reserveVehicle(vehicleId)).thenReturn(false);

        ResponseEntity<String> response = vehicleController.reserveVehicle(vehicleId);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("vehicle already reserved", response.getBody());
    }

    @Test
    void testUnReserveVehicleSuccess() {
        Long vehicleId = 1L;
        when(vehicleService.unReserveVehicle(vehicleId)).thenReturn(true);

        ResponseEntity<String> response = vehicleController.unReserveVehicle(vehicleId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("1", response.getBody());
    }

    @Test
    void testUnReserveVehicleFailure() {
        Long vehicleId = 1L;
        when(vehicleService.unReserveVehicle(vehicleId)).thenReturn(false);

        ResponseEntity<String> response = vehicleController.unReserveVehicle(vehicleId);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("reservation cancelation failed", response.getBody());
    }
}
