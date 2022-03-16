package se.knowit.iz.carbonrebound.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import se.knowit.iz.carbonrebound.entities.Role;

public interface RoleRepo extends JpaRepository< Role, String > {
    Role findByName(String name);
}
