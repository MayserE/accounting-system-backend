package com.accounting_system.accounting_system_backend.exceptions.companies;

import com.accounting_system.accounting_system_backend.exceptions.EntityNotFoundException;

public class CompanyNotFoundException extends EntityNotFoundException {
    public CompanyNotFoundException(String message) {
        super(message);
    }
}
