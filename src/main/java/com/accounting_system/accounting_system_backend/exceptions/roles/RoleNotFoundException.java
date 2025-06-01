package com.accounting_system.accounting_system_backend.exceptions.roles;

import com.accounting_system.accounting_system_backend.exceptions.EntityNotFoundException;

public class RoleNotFoundException extends EntityNotFoundException {
    public RoleNotFoundException(String message) {
        super(message);
    }
}
