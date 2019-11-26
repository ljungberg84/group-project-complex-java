package se.complexjava.videostreamingapi.exceptionhandling.exception;

public class ResourceCreationException extends Exception {

    public ResourceCreationException(String message) {
        super(message);
    }

    public ResourceCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
