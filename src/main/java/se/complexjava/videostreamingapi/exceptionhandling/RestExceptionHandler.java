package se.complexjava.videostreamingapi.exceptionhandling;


import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import se.complexjava.videostreamingapi.exceptionhandling.exception.ResourceCreationException;
import se.complexjava.videostreamingapi.exceptionhandling.exception.ResourceNotFoundException;


@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    //TODO: make a response error serializable class to return in responseBody

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity handleResourceNotFound(ResourceNotFoundException e){

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage());
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity handleConstraintViolation(DataIntegrityViolationException e){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMostSpecificCause().getMessage());
    }


    @ExceptionHandler(ResourceCreationException.class)
    public ResponseEntity handleResourceCreationException(ResourceCreationException e){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
    }
}
