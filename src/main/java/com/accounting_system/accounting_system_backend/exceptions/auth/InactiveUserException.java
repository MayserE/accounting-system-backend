package com.accounting_system.accounting_system_backend.exceptions.auth;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InactiveUserException extends Exception {
    public InactiveUserException() {
        super("Usuario inactivo.");
    }
}
