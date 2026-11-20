package se.yrgo.carworkshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se.yrgo.carworkshop.domain.Customer;
import se.yrgo.carworkshop.domain.Vehicle;
import se.yrgo.carworkshop.dto.VehicleDTO;
import se.yrgo.carworkshop.repository.CustomerRepository;
import se.yrgo.carworkshop.repository.VehicleRepository;
import se.yrgo.carworkshop.projection.VehicleBrandProjection;
import se.yrgo.carworkshop.dto.VehicleBrandDTO;

import java.util.ArrayList;
import java.util.List;


@RestController
public class VehicleController {
    private final VehicleRepository vehicleRepository;
    private final CustomerRepository customerRepository;

    public VehicleController(VehicleRepository vehicleRepository, CustomerRepository customerRepository) {
        this.vehicleRepository = vehicleRepository;
        this.customerRepository = customerRepository;
    }

    @PostMapping("/vehicles")
    public VehicleDTO createVehicle(
            @RequestParam String registrationNumber,
            @RequestParam String brand,
            @RequestParam String model,
            @RequestParam int productionYear) {

        Vehicle vehicle = new Vehicle(registrationNumber, brand, model, productionYear);
        Vehicle savedVehicle = vehicleRepository.save(vehicle);

        return new VehicleDTO(
                savedVehicle.getId(),
                savedVehicle.getRegistrationNumber(),
                savedVehicle.getBrand(),
                savedVehicle.getModel(),
                savedVehicle.getProductionYear()

        );

    }


    @GetMapping("/vehicles")
    public List<VehicleDTO> getAllVehicles() {

        List<Vehicle> allVehicles = vehicleRepository.findAll();
        List<VehicleDTO> vehicleDTOs = new ArrayList<>();

        for (Vehicle vehicle :
                allVehicles) {

            VehicleDTO vehicleDTO = new VehicleDTO(
                    vehicle.getId(),
                    vehicle.getRegistrationNumber(),
                    vehicle.getBrand(),
                    vehicle.getModel(),
                    vehicle.getProductionYear()
            );

            vehicleDTOs.add(vehicleDTO);
        }
        return vehicleDTOs;
    }

    @GetMapping("/vehicles-by-brand")
    public List<VehicleDTO> getVehiclesByBrand(@RequestParam String brand) {
        List<Vehicle> allVehiclesOfThisBrand = vehicleRepository.findByBrand(brand);
        List<VehicleDTO> vehicleDTOs = new ArrayList<>();

        for (Vehicle vehicle :
                allVehiclesOfThisBrand) {

            VehicleDTO vehicleDTO = new VehicleDTO(
                    vehicle.getId(),
                    vehicle.getRegistrationNumber(),
                    vehicle.getBrand(),
                    vehicle.getModel(),
                    vehicle.getProductionYear()
            );

            vehicleDTOs.add(vehicleDTO);
        }
        return vehicleDTOs;
    }


    @PostMapping("/addvehicletocustomer")
    public void addVehicleToCustomer(
            @RequestParam Long customerId,
            @RequestParam Long vehicleId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Customer not found"));
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Vehicle not found"));

        vehicle.setCustomer(customer);
        vehicleRepository.save(vehicle);
    }

    @PutMapping("/vehicles/{id}/brand")
    public void updateVehicleBrand(
            @PathVariable Long id,
            @RequestParam String brand,
            @RequestParam String model) {
        int updated = vehicleRepository.updateVehicleBrandAndModel(id, brand, model);

        if (updated == 0) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Vehicle not found"
            );
        }
    }

    @GetMapping("/vehicle-brand-info")
    public List<VehicleBrandDTO> getVehicleBrandInfo() {

        List<VehicleBrandProjection> projections =
                vehicleRepository.findVehicleBrandInfo();

        List<VehicleBrandDTO> vehicleBrandDTOs = new ArrayList<>();

        for (VehicleBrandProjection projection : projections) {

            VehicleBrandDTO vehicleBrandDTO = new VehicleBrandDTO(
                    projection.getRegistrationNumber(),
                    projection.getBrand()
            );

            vehicleBrandDTOs.add(vehicleBrandDTO);
        }

        return vehicleBrandDTOs;
    }
}