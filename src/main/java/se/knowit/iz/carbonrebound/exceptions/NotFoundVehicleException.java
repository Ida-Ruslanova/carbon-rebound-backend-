package se.knowit.iz.carbonrebound.exceptions;

public class NotFoundVehicleException extends Exception {
    public NotFoundVehicleException(String message) {
        super(message);
    }

    public NotFoundVehicleException() {
        super();
    }
}
