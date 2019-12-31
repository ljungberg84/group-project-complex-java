package se.complexjava.videostreamingapi.exceptionhandling;


import com.mysql.cj.jdbc.exceptions.CommunicationsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import se.complexjava.videostreamingapi.exceptionhandling.exception.ResourceCreationException;
import se.complexjava.videostreamingapi.exceptionhandling.exception.ResourceNotFoundException;

import javax.validation.ConstraintViolationException;


@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    //TODO: make a response error serializable class to return in responseBody
    //com.mysql.cj.jdbc.exceptions.CommunicationsException

    private Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(CommunicationsException.class)
    public void handleCommunicationsException(CommunicationsException e){

        logger.info("{}. database could be unavailable(this is normal on startup)", e.getMessage());

    }

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

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity handleConstraintViolationException(ConstraintViolationException e){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error saving entity, please check your data");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception e){
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body("Exception: " + e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleRuntimeException(RuntimeException e){
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body("Runtime Exception: " + e.getMessage());
    }
}
