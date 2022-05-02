package se.knowit.iz.carbonrebound.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import se.knowit.iz.carbonrebound.entities.Vehicle;

public interface VehicleRepository extends JpaRepository< Vehicle, Long > {
}
