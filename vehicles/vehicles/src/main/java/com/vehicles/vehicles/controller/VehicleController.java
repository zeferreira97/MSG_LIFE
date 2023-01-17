package com.vehicles.vehicles.controller;

import com.vehicles.vehicles.Exceptions.ResourceNotFoundException;
import com.vehicles.vehicles.Service.VehicleServiceInterface;
import com.vehicles.vehicles.model.Vehicle;
import com.vehicles.vehicles.model.VehicleCreateRequest;
import com.vehicles.vehicles.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/msg_life")
public class VehicleController {

    @Autowired
    private VehicleServiceInterface vehicleServiceInterface;

    @Autowired
    private VehicleRepository vehicleRepository;



    @GetMapping("/vehicle")
    public List<Vehicle> getAllVehicles() throws ResourceNotFoundException{
        return vehicleServiceInterface.getAllVehicles();
    }


    @PatchMapping("/vehicle/riskFactor")
    public Vehicle updateVehicleRiskFactor(@RequestBody VehicleCreateRequest vehicle) throws ResourceNotFoundException {
        return vehicleServiceInterface.updateVehicleRiskFactor(vehicle);
    }


    @PostMapping(value="/vehicle")
    @ResponseStatus(code = HttpStatus.OK)
    public Vehicle createVehicle(@RequestBody VehicleCreateRequest vehicle) throws Exception{
        return vehicleServiceInterface.createVehicle(vehicle);
    }

}
