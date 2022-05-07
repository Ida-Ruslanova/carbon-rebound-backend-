package se.knowit.iz.carbonrebound.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import se.knowit.iz.carbonrebound.entities.PrivateVehicle;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository< PrivateVehicle, Long > {

    @Query("select v from PrivateVehicle v where v.registrationNumber = :registrationNumber")
    Optional<PrivateVehicle> getByRegistrationNumber(String registrationNumber);
}
