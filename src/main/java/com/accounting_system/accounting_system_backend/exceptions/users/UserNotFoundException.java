package com.accounting_system.accounting_system_backend.exceptions.users;

import com.accounting_system.accounting_system_backend.exceptions.EntityNotFoundException;

public class UserNotFoundException extends EntityNotFoundException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
