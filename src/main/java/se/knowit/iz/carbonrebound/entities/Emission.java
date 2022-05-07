package se.knowit.iz.carbonrebound.entities;

import se.knowit.iz.carbonrebound.vehiclesdetails.VehiclesDetails;

import javax.persistence.*;

@Entity
@Table(name = "emissions")
public class Emission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    private VehiclesDetails.typeOfVehicles typeOfVehicle;
    private String dateOfTrip;
    private String distanceFrom;
    private String distanceTo;
    private Long vehicleId;
    private double distance;
    private double totalEmission;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Emission(VehiclesDetails.typeOfVehicles typeOfVehicle,
                    String dateOfTrip,
                    String distanceFrom,
                    String distanceTo,
                    Long vehicleId,
                    double distance,
                    double totalEmission,
                    User user) {
        this.typeOfVehicle = typeOfVehicle;
        this.dateOfTrip = dateOfTrip;
        this.distanceFrom = distanceFrom;
        this.distanceTo = distanceTo;
        this.vehicleId = vehicleId;
        this.distance = distance;
        this.totalEmission = totalEmission;
        this.user = user;
    }

    public Emission() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VehiclesDetails.typeOfVehicles getTypeOfVehicle() {
        return typeOfVehicle;
    }

    public void setTypeOfVehicle(VehiclesDetails.typeOfVehicles typeOfVehicle) {
        this.typeOfVehicle = typeOfVehicle;
    }

    public String getDateOfTrip() {
        return dateOfTrip;
    }

    public void setDateOfTrip(String dateOfTrip) {
        this.dateOfTrip = dateOfTrip;
    }

    public String getDistanceFrom() {
        return distanceFrom;
    }

    public void setDistanceFrom(String distanceFrom) {
        this.distanceFrom = distanceFrom;
    }

    public String getDistanceTo() {
        return distanceTo;
    }

    public void setDistanceTo(String distanceTo) {
        this.distanceTo = distanceTo;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getTotalEmission() {
        return totalEmission;
    }

    public void setTotalEmission(double totalEmission) {
        this.totalEmission = totalEmission;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
