package com.accounting_system.accounting_system_backend.exceptions.users;

import com.accounting_system.accounting_system_backend.exceptions.EntityAlreadyExistsException;

public class UserAlreadyExistsException extends EntityAlreadyExistsException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
