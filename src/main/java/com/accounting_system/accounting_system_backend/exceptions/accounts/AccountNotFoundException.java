package com.accounting_system.accounting_system_backend.exceptions.accounts;

import com.accounting_system.accounting_system_backend.exceptions.EntityNotFoundException;

public class AccountNotFoundException extends EntityNotFoundException {
    public AccountNotFoundException(String message) {
        super(message);
    }
}
