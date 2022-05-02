package se.knowit.iz.carbonrebound.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.knowit.iz.carbonrebound.entities.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository< User, Long > {
    Optional< User > findByEmail(String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
