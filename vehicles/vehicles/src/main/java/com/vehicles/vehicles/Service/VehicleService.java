package com.vehicles.vehicles.Service;

import com.vehicles.vehicles.Exceptions.ResourceNotFoundException;
import com.vehicles.vehicles.client.VehicleClient;
import com.vehicles.vehicles.model.Vehicle;
import com.vehicles.vehicles.model.VehicleCreateRequest;
import com.vehicles.vehicles.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import static java.util.Objects.isNull;


@Service
public class VehicleService implements VehicleServiceInterface{

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleClient vehicleClient;

    @Override
    public List<Vehicle> getAllVehicles() throws ResourceNotFoundException {

        try {
            List<Vehicle> vehicles = new ArrayList<>();

            vehicleRepository.findAll().forEach(vehicles::add);

            if (vehicles.isEmpty()) {
                return vehicles;
            }

            for (Vehicle vehicle : vehicles) {
                Locale test = new Locale("English", vehicle.getCountryOfLicense());
                vehicle.setCountryOfLicense(vehicle.getCountryOfLicense() + " -> " + test.getDisplayCountry());
            }

            return vehicles;
        } catch (Exception e) {
            throw new ResourceNotFoundException("No vehicles were found!");
        }
    }


    @Override
    public Vehicle updateVehicleRiskFactor(VehicleCreateRequest vehicle)
            throws ResourceNotFoundException {

        Vehicle vehicle1;

        try {
            vehicle1 = vehicleRepository.findByLicenseAndCountryOfLicense(vehicle.getLicense(), vehicle.getCountryOfLicense());
            vehicle1.setRiskFactor(vehicle.getRiskFactor());
            vehicleRepository.save(vehicle1);
        }catch (Exception e){
            throw new ResourceNotFoundException("Vehicle not found for this license : " + vehicle.getLicense() + " and country : " + vehicle.getCountryOfLicense());
        }

        return vehicle1;
    }


    @Override
    public Vehicle createVehicle(VehicleCreateRequest vehicle) throws Exception{

        List<Vehicle> vehicleList = vehicleClient.consultVehicles();

        Vehicle finalVehicle= new Vehicle(vehicle.getLicense(),vehicle.getCountryOfLicense(),vehicle.getRiskFactor(),null,null,null);

        try {
            for (Vehicle value : vehicleList) {
                if (value.getLicense().equals(vehicle.getLicense()) && (value.getCountryOfLicense().equals(vehicle.getCountryOfLicense()))) {

                    if (isNull(vehicleRepository.findByLicenseAndCountryOfLicense(vehicle.getLicense(),vehicle.getCountryOfLicense()))) {

                        finalVehicle.setNumberDoors(value.getNumberDoors());
                        finalVehicle.setFuelType(value.getFuelType());
                        finalVehicle.setPower(value.getPower());

                        vehicleRepository.save(finalVehicle);
                        return finalVehicle;
                    }
                }
            }
            if(isNull(finalVehicle.getPower())){
                throw new ResourceNotFoundException("Vehicle can't be created because if already exists on the Database or can't be found on the service");
            }
        }catch (Exception e){
            throw new ResourceNotFoundException("Vehicle can't be created because if already exists on the Database or can't be found on the service");
        }
        return finalVehicle;
    }


}
