package se.yrgo.carworkshop.dto;

import java.util.ArrayList;
import java.util.List;

public class CustomerDTO {
    private Long id;
    private String name;
    private String phoneNumber;
    private List<VehicleDTO> ownedVehicles;

    public CustomerDTO() {
        this.ownedVehicles = new ArrayList<>();
    }

    public CustomerDTO(Long id, String name, String phoneNumber, List<VehicleDTO> ownedVehicles) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.ownedVehicles = ownedVehicles;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public List<VehicleDTO> getOwnedVehicles() {
        return ownedVehicles;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
