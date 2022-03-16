package se.knowit.iz.carbonrebound.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import se.knowit.iz.carbonrebound.entities.User;

public interface UserRepo extends JpaRepository< User, String > {
    User findByUserName(String userName);
}
