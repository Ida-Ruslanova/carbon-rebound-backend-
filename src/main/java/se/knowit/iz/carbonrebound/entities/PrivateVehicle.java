package se.knowit.iz.carbonrebound.entities;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "vehicles")
public class PrivateVehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(max = 20)
    private String registrationNumber;
    @NotBlank
    @Size(max = 20)
    private String brand;
    @NotBlank
    @Size(max = 50)
    private String model;
    @NotBlank
    @Size(max = 120)
    private String year;
    @NotNull
    private int CO2Emissions;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public PrivateVehicle(Long id, String registrationNumber, String brand, String model, String year, int CO2Emissions, User user) {
        this.id = id;
        this.registrationNumber = registrationNumber;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.CO2Emissions = CO2Emissions;
        this.user = user;
    }

    public PrivateVehicle() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getCO2Emissions() {
        return CO2Emissions;
    }

    public void setCO2Emissions(int CO2Emissions) {
        this.CO2Emissions = CO2Emissions;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
