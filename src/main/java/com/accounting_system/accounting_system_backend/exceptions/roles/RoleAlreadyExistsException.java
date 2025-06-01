package com.accounting_system.accounting_system_backend.exceptions.roles;

import com.accounting_system.accounting_system_backend.exceptions.EntityAlreadyExistsException;

public class RoleAlreadyExistsException extends EntityAlreadyExistsException {
    public RoleAlreadyExistsException(String message) {
        super(message);
    }
}
