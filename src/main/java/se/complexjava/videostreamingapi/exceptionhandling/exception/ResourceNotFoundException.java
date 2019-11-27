package se.complexjava.videostreamingapi.exceptionhandling.exception;

public class ResourceNotFoundException extends Exception {

    public ResourceNotFoundException(String message) {

        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {

        super(message, cause);
    }
}
