package com.accounting_system.accounting_system_backend.exceptions.clients;

import com.accounting_system.accounting_system_backend.exceptions.EntityAlreadyExistsException;

public class ClientAlreadyExistsException extends EntityAlreadyExistsException {
    public ClientAlreadyExistsException(String message) {
        super(message);
    }
}
