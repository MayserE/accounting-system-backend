package com.accounting_system.accounting_system_backend.exceptions.company_users;

import com.accounting_system.accounting_system_backend.exceptions.EntityNotFoundException;

public class CompanyUserNotFoundException extends EntityNotFoundException {
    public CompanyUserNotFoundException(String message) {
        super(message);
    }
}
