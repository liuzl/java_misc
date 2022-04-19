package com.example.springboottutorial.errors;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private HttpStatus status;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<String> details;

    public ErrorResponse(final HttpStatus status, final String message) {
        this.status = status;
        this.message = message;
    }

    public ErrorResponse(final HttpStatus status, final String message, final String error) {
        this.status = status;
        this.message = message;
        this.details = Arrays.asList(error);
    }
}

