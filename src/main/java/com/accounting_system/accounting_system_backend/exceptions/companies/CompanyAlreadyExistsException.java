package com.accounting_system.accounting_system_backend.exceptions.companies;

import com.accounting_system.accounting_system_backend.exceptions.EntityAlreadyExistsException;

public class CompanyAlreadyExistsException extends EntityAlreadyExistsException {
    public CompanyAlreadyExistsException(String message) {
        super(message);
    }
}
