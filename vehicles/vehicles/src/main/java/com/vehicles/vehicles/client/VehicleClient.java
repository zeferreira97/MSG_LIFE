package com.vehicles.vehicles.client;

import com.vehicles.vehicles.model.Vehicle;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.MediaType;

import java.util.List;

@FeignClient(name = "vehicles", url= "${vehicles.baseUrl}")
public interface VehicleClient {

    @GetMapping(value = "/${vehicles.retrieve}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<Vehicle> consultVehicles();
}
