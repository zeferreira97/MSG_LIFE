package com.vehicles.vehicles.Service;

import com.vehicles.vehicles.Exceptions.ResourceNotFoundException;
import com.vehicles.vehicles.model.Vehicle;
import com.vehicles.vehicles.model.VehicleCreateRequest;

import java.util.List;

public interface VehicleServiceInterface {

    List<Vehicle> getAllVehicles() throws ResourceNotFoundException;
    Vehicle updateVehicleRiskFactor(VehicleCreateRequest vehicle) throws ResourceNotFoundException;
    Vehicle createVehicle(VehicleCreateRequest vehicle) throws Exception;
    }
