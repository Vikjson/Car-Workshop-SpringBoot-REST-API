package se.yrgo.carworkshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import se.yrgo.carworkshop.domain.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByName(String name);

    @Query("SELECT c, v FROM Customer c LEFT JOIN c.ownedVehicles v")
    List<Object[]> findAllCustomersWithVehicles();
}
