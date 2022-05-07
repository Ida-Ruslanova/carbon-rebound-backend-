package se.knowit.iz.carbonrebound.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import se.knowit.iz.carbonrebound.entities.Emission;

public interface EmissionRepository extends JpaRepository< Emission, Long > {}
