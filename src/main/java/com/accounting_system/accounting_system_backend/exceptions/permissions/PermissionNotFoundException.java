package com.accounting_system.accounting_system_backend.exceptions.permissions;

import com.accounting_system.accounting_system_backend.exceptions.EntityNotFoundException;

public class PermissionNotFoundException extends EntityNotFoundException {
    public PermissionNotFoundException(String message) {
        super(message);
    }
}
