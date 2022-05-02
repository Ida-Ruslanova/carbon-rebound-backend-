package se.knowit.iz.carbonrebound.security.payload.request;

import java.util.Set;

public class SignupRequest {
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;
    private Set< String > role;

    public SignupRequest(String firstname, String lastname, String username, String email, String password, Set< String > role) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set< String > getRole() {
        return role;
    }

    public void setRole(Set< String > role) {
        this.role = role;
    }
}
