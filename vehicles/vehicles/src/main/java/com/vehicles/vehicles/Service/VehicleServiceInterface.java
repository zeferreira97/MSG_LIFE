package com.vehicles.vehicles.Service;

import com.vehicles.vehicles.model.Vehicle;
import com.vehicles.vehicles.model.VehicleCreateRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VehicleServiceInterface {

    ResponseEntity<List<Vehicle>> getAllVehicles();
    ResponseEntity<Vehicle> createVehicle(Vehicle vehicle);
    //ResponseEntity<Vehicle> createVehicleFirstStep(VehicleCreateRequest vehicle);
    }
