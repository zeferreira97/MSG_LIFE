package com.vehicles.vehicles.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name ="vehicles")
public class VehicleCreateRequest {


    @Id
    @Column(name = "license", nullable = false)
    private String license;

    @Column(name = "country", nullable = false)
    private String countryOfLicense;

    @Column(name = "risk_factor", nullable = false)
    private double riskFactor;
}