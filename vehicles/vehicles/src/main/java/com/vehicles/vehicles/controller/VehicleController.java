package com.vehicles.vehicles.controller;

import com.vehicles.vehicles.Exceptions.ResourceNotFoundException;
import com.vehicles.vehicles.Service.VehicleServiceInterface;
import com.vehicles.vehicles.model.Vehicle;
import com.vehicles.vehicles.model.VehicleCreateRequest;
import com.vehicles.vehicles.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/msg_life")
public class VehicleController {

    @Autowired
    private VehicleServiceInterface vehicleServiceInterface;

    @Autowired
    private VehicleRepository vehicleRepository;

    @PostMapping(value="/vehicle")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Vehicle> createVehicle(@RequestBody Vehicle vehicle) {
        return vehicleServiceInterface.createVehicle(vehicle);
    }


    @GetMapping("/vehicle")
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        return vehicleServiceInterface.getAllVehicles();
    }


    @PatchMapping("/vehicle/{license}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable(value = "license") String license, @RequestParam BigDecimal riskFactor) throws ResourceNotFoundException {
        return vehicleServiceInterface.updateVehicle(license, riskFactor);
    }

/*
    @PostMapping(value="/vehicle")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Vehicle> createVehicleFirstStep(@RequestBody VehicleCreateRequest vehicle) {
        return vehicleServiceInterface.createVehicleFirstStep(vehicle);
    }*/
}
