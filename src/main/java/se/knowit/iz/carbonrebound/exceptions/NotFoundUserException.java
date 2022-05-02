package se.knowit.iz.carbonrebound.exceptions;

public class NotFoundUserException extends Exception {
    public NotFoundUserException(String message) {
        super(message);
    }

    public NotFoundUserException() {
        super();
    }
}
