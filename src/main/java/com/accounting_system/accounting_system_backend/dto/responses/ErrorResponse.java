package com.accounting_system.accounting_system_backend.dto.responses;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.time.Instant;
import java.util.Date;

@Getter
public class ErrorResponse {
    private final Date timestamp;
    private final Integer status;
    private final String error;
    private final Class<? extends Exception> exception;
    private final String message;
    private final String path;

    public ErrorResponse(HttpStatus httpStatus, Class<? extends Exception> exception, String message, String path) {
        this(httpStatus.value(), httpStatus.getReasonPhrase(), exception, message, path);
    }

    public ErrorResponse(
            HttpStatusCode httpStatusCode,
            Class<? extends Exception> exception,
            String message,
            String path
    ) {
        this(HttpStatus.valueOf(httpStatusCode.value()), exception, message, path);
    }

    public ErrorResponse(HttpStatus httpStatus, String message, String path) {
        this(httpStatus, null, message, path);
    }

    private ErrorResponse(
            Integer status,
            String error,
            Class<? extends Exception> exception,
            String message,
            String path
    ) {
        this.timestamp = Date.from(Instant.now());
        this.status = status;
        this.error = error;
        this.exception = exception;
        this.message = message;
        this.path = path;
    }
}
