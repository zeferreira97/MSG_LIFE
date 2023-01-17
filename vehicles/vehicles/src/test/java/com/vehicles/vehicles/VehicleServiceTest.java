package com.vehicles.vehicles;

import com.vehicles.vehicles.Exceptions.ResourceNotFoundException;
import com.vehicles.vehicles.Service.VehicleService;
import com.vehicles.vehicles.Service.VehicleServiceInterface;
import com.vehicles.vehicles.client.VehicleClient;
import com.vehicles.vehicles.model.Vehicle;
import com.vehicles.vehicles.model.VehicleCreateRequest;
import com.vehicles.vehicles.repository.VehicleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class VehicleServiceTest{

    @InjectMocks
    VehicleService vehicleService;

    @Mock
    VehicleServiceInterface vehicleServiceInterface;

    @Mock
    VehicleRepository vehicleRepository;

    @Mock
    VehicleClient vehicleClient;


    @Test
    public void createVehicleOk() throws Exception{


        VehicleCreateRequest vehicleCreateRequest = new VehicleCreateRequest("UU 32 KW", "FR", 0.07);
        Vehicle vehicleFinal = new Vehicle("UU 32 KW", "FR", 0.07,null,null,null);

        Vehicle vehicle1 = new Vehicle("UU 32 KW", "FR", 0.07, 4, "Diesel", 234);

        List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList.add(vehicle1);

        when(vehicleClient.consultVehicles()).thenReturn(vehicleList);

        List<Vehicle> vehiclesInService = vehicleClient.consultVehicles();


        when(vehicleRepository.findByPower(vehiclesInService.get(0).getPower())).thenReturn(null);

        vehicleRepository.findByPower(vehiclesInService.get(0).getPower());

        vehicleFinal.setPower(vehiclesInService.get(0).getPower());
        vehicleFinal.setNumberDoors(vehiclesInService.get(0).getNumberDoors());
        vehicleFinal.setFuelType(vehiclesInService.get(0).getFuelType());

        when(vehicleServiceInterface.createVehicle(vehicleCreateRequest)).thenReturn(vehicleFinal);

        Vehicle output = vehicleServiceInterface.createVehicle(vehicleCreateRequest);

        assertEquals(vehicleFinal,output);
    }

    @Test
    public void createVehicleNotOk() throws Exception{


        VehicleCreateRequest vehicleCreateRequest = new VehicleCreateRequest("UU 32 KW", "FR", 0.07);
        Vehicle vehicleFinal = new Vehicle("UU 32 KW", "FR", 0.07,null,null,null);

        Vehicle vehicle1 = new Vehicle("UU 32 KW", "FR", 0.07, 4, "Diesel", 234);

        List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList.add(vehicle1);

        when(vehicleClient.consultVehicles()).thenReturn(vehicleList);

        List<Vehicle> vehiclesInService = vehicleClient.consultVehicles();


        when(vehicleRepository.findByPower(vehiclesInService.get(0).getPower())).thenReturn(null);

        vehicleRepository.findByPower(vehiclesInService.get(0).getPower());

        Vehicle vehicleTest = new Vehicle("UU 32 KW", "FR", 0.07,3,"Diesel",255);


        when(vehicleServiceInterface.createVehicle(vehicleCreateRequest)).thenReturn(vehicleTest);

        Vehicle output = vehicleServiceInterface.createVehicle(vehicleCreateRequest);

        assertNotEquals(vehicleFinal,output);
    }


    @Test
    public void updateVehicleOk() throws ResourceNotFoundException {

        //Test repository method
        Vehicle vehicleinService = new Vehicle("76 GH 90", "IN", 0.91, 3, "Diesel", 99);

        when(vehicleRepository.findByLicenseAndCountryOfLicense("76 GH 90", "IN")).thenReturn(vehicleinService);

        Vehicle vehicleCompare = vehicleRepository.findByLicenseAndCountryOfLicense("76 GH 90", "IN");

        assertEquals(vehicleCompare,vehicleinService);


        //Test update
        VehicleCreateRequest vehicleCreateRequest = new VehicleCreateRequest("76 GH 90", "IN", 0.05);
        Vehicle vehicleUpdated = new Vehicle("76 GH 90", "IN", 0.05, 3, "Diesel", 99);


        when(vehicleServiceInterface.updateVehicleRiskFactor(vehicleCreateRequest)).thenReturn(vehicleUpdated);
        Vehicle vehicleUpdated2 = vehicleServiceInterface.updateVehicleRiskFactor(vehicleCreateRequest);


        assertEquals(vehicleUpdated, vehicleUpdated2);

    }


    @Test
    public void updateVehicleNotOk() throws ResourceNotFoundException {

        //Test repository method
        Vehicle vehicleinService = new Vehicle("76 GH 90", "IN", 0.91, 3, "Petrol", 200);

        Vehicle vehicleCompare = vehicleRepository.findByLicenseAndCountryOfLicense("76 GH 90", "AA");

        assertNotEquals(vehicleCompare,vehicleinService);


        //Test update
        VehicleCreateRequest vehicleCreateRequest = new VehicleCreateRequest("76 GH 90", "IN", 0.05);
        Vehicle vehicleUpdated = new Vehicle("76 GH 90", "IN", 0.99, 7, "Diesel", 99);

        Vehicle vehicleUpdated2 = vehicleServiceInterface.updateVehicleRiskFactor(vehicleCreateRequest);


        assertNotEquals(vehicleUpdated, vehicleUpdated2);

    }


    @Test
    public void getAllVehiclesOk(){

        Vehicle vehicle1 = new Vehicle("76 GH 90", "IN", 0.05, null, null, null);
        Vehicle vehicle2 = new Vehicle("AA 00 AA", "BV", 0.2, null, null, null);
        Vehicle vehicle3 = new Vehicle("87 88 BV", "AR", 0.02, null, null, null);


        List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList.add(vehicle1);
        vehicleList.add(vehicle2);
        vehicleList.add(vehicle3);

        when(vehicleRepository.findAll()).thenReturn(vehicleList);

        List<Vehicle> vehicleList1 = new ArrayList<>();


        vehicleRepository.findAll().forEach(vehicleList1::add);

        assertEquals(vehicleList,vehicleList1);

    }
}
