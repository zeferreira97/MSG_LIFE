package com.vehicles.vehicles.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name ="vehicles")
public class VehicleCreateRequest {


    private String license;
    private String countryOfLicense;
    private Double riskFactor;


    public VehicleCreateRequest(){
    }

    public VehicleCreateRequest(String license, String countryOfLicense, Double riskFactor, Integer numberDoors, String fuelType, Integer power) {
        this.license = license;
        this.countryOfLicense = countryOfLicense;
        this.riskFactor = riskFactor;
    }

    @Id
    @Column(name = "license", nullable = false)
    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    @Column(name = "country", nullable = false)
    public String getCountryOfLicense() {
        return countryOfLicense;
    }

    public void setCountryOfLicense(String countryOfLicense) {
        this.countryOfLicense = countryOfLicense;
    }

    @Column(name = "risk_factor", nullable = false)
    public Double getRiskFactor() {
        return riskFactor;
    }

    public void setRiskFactor(Double riskFactor) {
        this.riskFactor = riskFactor;
    }


    @Override
    public String toString() {
        return "VehicleCreateRequest{" +
                "license='" + license + '\'' +
                ", countryOfLicense='" + countryOfLicense + '\'' +
                ", riskFactor=" + riskFactor +
                '}';
    }
}