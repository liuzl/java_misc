package com.example.springboottutorial.handler;

import com.example.springboottutorial.errors.ErrorResponse;
import com.example.springboottutorial.exception.DetailErrorException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler({DetailErrorException.class})
    public ResponseEntity<Object> handleDetailErrorException(final DetailErrorException ex) {
        final ErrorResponse error = new ErrorResponse(ex.getStatus(), ex.getMessage(), ex.getDetails());
        return new ResponseEntity<>(error, new HttpHeaders(), ex.getStatus());
    }

    @ExceptionHandler({ResponseStatusException.class})
    public ResponseEntity<Object> handleResponseStatusException(final ResponseStatusException ex) {
        final ErrorResponse error = new ErrorResponse(ex.getStatus(), ex.getReason());
        return new ResponseEntity<>(error, new HttpHeaders(), ex.getStatus());
    }

    // 500
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAll(final Exception ex) {
        final ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), "error occurred");
        return new ResponseEntity<>(error, new HttpHeaders(), error.getStatus());
    }
}

