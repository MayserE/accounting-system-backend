package com.accounting_system.accounting_system_backend.exceptions.vendors;

import com.accounting_system.accounting_system_backend.exceptions.EntityAlreadyExistsException;

public class VendorAlreadyExistsException extends EntityAlreadyExistsException {
    public VendorAlreadyExistsException(String message) {
        super(message);
    }
}
