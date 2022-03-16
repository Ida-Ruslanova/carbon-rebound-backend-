package se.knowit.iz.carbonrebound.services;

import se.knowit.iz.carbonrebound.entities.Role;
import se.knowit.iz.carbonrebound.entities.User;

import java.util.List;

public interface UserServiceSecurity {
    User saveUser (User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String rolename);
    User getUser (String username);
    List< User >getUsers();
}
