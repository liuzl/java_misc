package com.example.springboottutorial.exception;

import lombok.Getter;
import org.springframework.core.NestedRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.util.List;

@Getter
public class DetailErrorException extends NestedRuntimeException {
    private final HttpStatus status;
    private final String message;
    @Nullable
    private final List<String> details;

    public DetailErrorException(HttpStatus status, String message) {
        this(status, message, null, null);
    }

    public DetailErrorException(HttpStatus status, String message, @Nullable List<String> details) {
        this(status, message, details, null);
    }

    public DetailErrorException(HttpStatus status, String message, @Nullable List<String> details, @Nullable Throwable cause) {
        super(null, cause);
        Assert.notNull(status, "status is required");
        Assert.notNull(message, "message is required");
        this.status = status;
        this.message = message;
        this.details = details;
    }

}
