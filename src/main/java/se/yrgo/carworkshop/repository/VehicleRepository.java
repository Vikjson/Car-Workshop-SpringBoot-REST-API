package se.yrgo.carworkshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import se.yrgo.carworkshop.domain.Vehicle;
import se.yrgo.carworkshop.projection.VehicleBrandProjection;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByBrand(String brand);

    @Query("SELECT v FROM Vehicle v WHERE v.customer.id = :customerId")
    List<Vehicle> findVehiclesByCustomerId(@Param("customerId") Long customerId);

    @Query("""
            SELECT v.registrationNumber AS registrationNumber, 
            v.brand AS brand 
            FROM Vehicle v""")
    List<VehicleBrandProjection> findVehicleBrandInfo();

    @Modifying
    @Transactional
    @Query("""
        UPDATE Vehicle v
        SET v.brand = :brand,
            v.model = :model
        WHERE v.id = :id
        """)
    int updateVehicleBrandAndModel(
            @Param("id") Long id,
            @Param("brand") String brand,
            @Param("model") String model
    );

    String model(String model);
}
