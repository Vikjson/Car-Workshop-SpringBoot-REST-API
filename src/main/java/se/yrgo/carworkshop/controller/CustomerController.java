package se.yrgo.carworkshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import se.yrgo.carworkshop.domain.Customer;
import se.yrgo.carworkshop.domain.Vehicle;
import se.yrgo.carworkshop.dto.CustomerDTO;
import se.yrgo.carworkshop.dto.VehicleDTO;
import se.yrgo.carworkshop.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CustomerController {

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @PostMapping("/customers")
    public CustomerDTO createCustomer(
            @RequestParam String name,
            @RequestParam String phone) {

        Customer customer = new Customer(name, phone);
        Customer savedCustomer = customerRepository.save(customer);

        return new CustomerDTO(
                savedCustomer.getId(),
                savedCustomer.getName(),
                savedCustomer.getPhoneNumber(),
                List.of()
        );

    }



    @GetMapping("/customer-id")
    public Long getCustomerId(@RequestParam String name) {

        Customer customer = customerRepository.findByName(name)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Customer not found"
                ));

        return customer.getId();
    }

    @GetMapping("/customers")
    public List<CustomerDTO> getAllCustomers() {

        List<Object[]> all =
                customerRepository.findAllCustomersWithVehicles();

        Map<Long, CustomerDTO> customerMap = new HashMap<>();

        for (Object[] result : all) {

            Customer customer = (Customer) result[0];
            Vehicle vehicle = (Vehicle) result[1];

            CustomerDTO customerDTO = customerMap.getOrDefault(
                    customer.getId(),
                    new CustomerDTO()
            );

            customerDTO.setId(customer.getId());
            customerDTO.setName(customer.getName());
            customerDTO.setPhoneNumber(customer.getPhoneNumber());

            if (vehicle != null) {

                VehicleDTO vehicleDTO = new VehicleDTO(
                        vehicle.getId(),
                        vehicle.getRegistrationNumber(),
                        vehicle.getBrand(),
                        vehicle.getModel(),
                        vehicle.getProductionYear()
                );

                customerDTO.getOwnedVehicles().add(vehicleDTO);
            }

            customerMap.put(customer.getId(), customerDTO);
        }

        return new ArrayList<>(customerMap.values());
    }
}
