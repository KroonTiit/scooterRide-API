package com.scooterRideApi.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.scooterRideApi.api.controller.RiderController;
import com.scooterRideApi.api.dto.RiderDTO;
import com.scooterRideApi.api.dto.mapper.RiderMapper;
import com.scooterRideApi.api.model.Rider;
import com.scooterRideApi.api.repository.RiderRepository;
import com.scooterRideApi.api.security.BearerTokenInterceptor;
import com.scooterRideApi.api.service.RiderService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RiderControllerTest {

    @InjectMocks
    private RiderController riderController;

    @Mock
    private RiderService riderService;

    @Mock
    private RiderRepository riderRepository;
    
    @Mock
    private BearerTokenInterceptor bearerTokenInterceptor;

    private RiderDTO riderDTO;

    private Rider rider;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        riderDTO = new RiderDTO("test@test.com", "password", "testuser");
        RiderMapper mapper = new RiderMapper();
        rider = mapper.mapToModel(riderDTO);
    }

    @Test
    void testRegisterUserSuccess() {
        when(riderService.registerUser(riderDTO)).thenReturn(rider);

        ResponseEntity<String> response = riderController.register(riderDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("User registered successfully", response.getBody());
        verify(riderService, times(1)).registerUser(riderDTO);
    }

    @Test
    void testRegisterUserFailure() {
        doThrow(new IllegalArgumentException("Invalid user data"))
                .when(riderService).registerUser(riderDTO);

        ResponseEntity<String> response = riderController.register(riderDTO);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid user data", response.getBody());
    }

    @Test
    void testLoginSuccess() {
        when(riderService.authenticateUser(riderDTO)).thenReturn(true);
        when(bearerTokenInterceptor.generateToken("testuser")).thenReturn("test-token");

        ResponseEntity<String> response = riderController.login(riderDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Login successful: test-token", response.getBody());
    }

    @Test
    void testLoginFailure() {
        when(riderService.authenticateUser(riderDTO)).thenReturn(false);

        ResponseEntity<String> response = riderController.login(riderDTO);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Invalid username or password", response.getBody());
    }
}
