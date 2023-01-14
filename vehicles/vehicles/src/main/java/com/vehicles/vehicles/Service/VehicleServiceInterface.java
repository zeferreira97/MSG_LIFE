package com.vehicles.vehicles.Service;

import com.vehicles.vehicles.Exceptions.ResourceNotFoundException;
import com.vehicles.vehicles.model.Vehicle;
import com.vehicles.vehicles.model.VehicleCreateRequest;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

public interface VehicleServiceInterface {

    ResponseEntity<List<Vehicle>> getAllVehicles();
    ResponseEntity<Vehicle> createVehicle(Vehicle vehicle);
    ResponseEntity<Vehicle> updateVehicle(String license, BigDecimal riskFactor) throws ResourceNotFoundException;
    //ResponseEntity<Vehicle> createVehicleFirstStep(VehicleCreateRequest vehicle);
    }
