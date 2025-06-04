package com.accounting_system.accounting_system_backend.exceptions.accounts;

import com.accounting_system.accounting_system_backend.exceptions.EntityAlreadyExistsException;

public class AccountAlreadyExistsException extends EntityAlreadyExistsException {
    public AccountAlreadyExistsException(String message) {
        super(message);
    }
}
