package com.accounting_system.accounting_system_backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends Exception {
    public BadRequestException(String message) {
        super(message);
    }
}
