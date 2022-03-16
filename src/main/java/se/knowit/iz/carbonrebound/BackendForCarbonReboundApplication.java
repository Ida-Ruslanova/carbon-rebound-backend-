package se.knowit.iz.carbonrebound;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import se.knowit.iz.carbonrebound.entities.Role;
import se.knowit.iz.carbonrebound.entities.User;
import se.knowit.iz.carbonrebound.services.UserServiceSecurity;

import java.util.ArrayList;
import java.util.UUID;

@SpringBootApplication
public class BackendForCarbonReboundApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendForCarbonReboundApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(UserServiceSecurity userServiceSecurity) {
        return args -> {
            userServiceSecurity.saveRole(new Role(UUID.randomUUID().toString(), "ROLE_USER"));
            userServiceSecurity.saveRole(new Role(UUID.randomUUID().toString(), "ROLE_MANAGER"));
            userServiceSecurity.saveRole(new Role(UUID.randomUUID().toString(), "ROLE_ADMIN"));
            userServiceSecurity.saveRole(new Role(UUID.randomUUID().toString(), "ROLE_SUPER_ADMIN"));

            userServiceSecurity.saveUser(new User(UUID.randomUUID().toString(), "John Travolta", "Travolta", "@jmail.com", "1234", "john", new ArrayList<>()));
            userServiceSecurity.saveUser(new User(UUID.randomUUID().toString(), "Will", "Smith", "@jmail.com", "1234", "will", new ArrayList<>()));
            userServiceSecurity.saveUser(new User(UUID.randomUUID().toString(), "Jim", "Carry", "@jmail.com", "1234", "jim", new ArrayList<>()));
            userServiceSecurity.saveUser(new User(UUID.randomUUID().toString(), "Arnold ", "Schwarzenegger", "@jmail.com", "1234", "arnold", new ArrayList<>()));

            userServiceSecurity.addRoleToUser("John", "ROLE_USER");
            userServiceSecurity.addRoleToUser("John", "ROLE_MANAGER");
            userServiceSecurity.addRoleToUser("Will", "ROLE_USER");
            userServiceSecurity.addRoleToUser("Will", "ROLE_SUPER_ADMIN");
            userServiceSecurity.addRoleToUser("Jim", "ROLE_USER");
            userServiceSecurity.addRoleToUser("Jim", "ROLE_MANAGER");
            userServiceSecurity.addRoleToUser("Arnold", "ROLE_USER");
            userServiceSecurity.addRoleToUser("Arnold", "ROLE_ADMIN");
            userServiceSecurity.addRoleToUser("Arnold", "ROLE_SUPER_ADMIN");
        };
    }
}
