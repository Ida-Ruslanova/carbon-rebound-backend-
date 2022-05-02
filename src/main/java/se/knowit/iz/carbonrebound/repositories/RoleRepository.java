package se.knowit.iz.carbonrebound.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.knowit.iz.carbonrebound.entities.ERole;
import se.knowit.iz.carbonrebound.entities.Role;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository< Role, Long > {
    Optional< Role > findByName(ERole name);
}
