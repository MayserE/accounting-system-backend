package com.accounting_system.accounting_system_backend.services;

import com.accounting_system.accounting_system_backend.dto.requests.RegisterItemRequest;
import com.accounting_system.accounting_system_backend.dto.responses.ItemResponse;
import com.accounting_system.accounting_system_backend.exceptions.items.ItemAlreadyExistsException;

import java.util.List;

public interface ItemService {
    List<ItemResponse> getItems();

    ItemResponse RegisterItem(RegisterItemRequest request) throws ItemAlreadyExistsException;
}
