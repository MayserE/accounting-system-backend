package com.accounting_system.accounting_system_backend.exceptions.items;

import com.accounting_system.accounting_system_backend.exceptions.EntityAlreadyExistsException;

public class ItemAlreadyExistsException extends EntityAlreadyExistsException {
    public ItemAlreadyExistsException(String message) {
        super(message);
    }
}
