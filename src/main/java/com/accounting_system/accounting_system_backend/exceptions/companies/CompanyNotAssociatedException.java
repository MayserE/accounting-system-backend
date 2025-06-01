package com.accounting_system.accounting_system_backend.exceptions.companies;

import com.accounting_system.accounting_system_backend.exceptions.EntityNotAssociatedException;

public class CompanyNotAssociatedException extends EntityNotAssociatedException {
    public CompanyNotAssociatedException(String message) {
        super(message);
    }
}
