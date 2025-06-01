package com.accounting_system.accounting_system_backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class EntityNotAssociatedException extends Exception {
    public EntityNotAssociatedException(String message) {
        super(message);
    }
}
