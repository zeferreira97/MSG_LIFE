package com.vehicles.vehicles.Service;

import com.vehicles.vehicles.Exceptions.ResourceNotFoundException;
import com.vehicles.vehicles.model.Vehicle;
import com.vehicles.vehicles.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


@Service
public class VehicleService implements VehicleServiceInterface{

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public ResponseEntity<List<Vehicle>> getAllVehicles() {

        try {
            List<Vehicle> vehicles = new ArrayList<Vehicle>();

            vehicleRepository.findAll().forEach(vehicles::add);

            if (vehicles.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            for(Integer i = 0; i <vehicles.size(); i++) {
                Locale test = new Locale("English", vehicles.get(i).getCountryOfLicense());
                vehicles.get(i).setCountryOfLicense(vehicles.get(i).getCountryOfLicense() + " -> " + test.getDisplayCountry());
            }

            return new ResponseEntity<>(vehicles, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @Override
    public ResponseEntity<Vehicle> createVehicle(Vehicle vehicle){

        try {
            Vehicle vehicle1 = vehicleRepository
                    .save(new Vehicle(vehicle.getLicense(), vehicle.getCountryOfLicense(), vehicle.getRiskFactor(), vehicle.getNumberDoors(), vehicle.getFuelType(),vehicle.getPower()));



            return new ResponseEntity<>(vehicle1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Vehicle> updateVehicle(String license, BigDecimal riskFactor)
            throws ResourceNotFoundException {

        Vehicle vehicle1 = vehicleRepository.findById(license).orElseThrow(() -> new ResourceNotFoundException("Vehicle not found for this license : " + license));

        vehicle1.setRiskFactor(riskFactor);
        vehicleRepository.save(vehicle1);

        return ResponseEntity.ok().body(vehicle1);
    }

/*
    @Override
    public ResponseEntity<Vehicle> createVehicleFirstStep(VehicleCreateRequest vehicle){

        Vehicle vehicle1 = new Vehicle(vehicle.getLicense(), vehicle.getCountryOfLicense(), vehicle.getRiskFactor());


    }
*/
}
