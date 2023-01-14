package com.vehicles.vehicles.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuxVehicle {

    @Column(name = "number_doors")
    private Integer numberDoors;

    @Column(name = "fuel_type", nullable = false)
    private String fuelType;

    @Column(name= "power", nullable = false)
    private Integer power;
}
