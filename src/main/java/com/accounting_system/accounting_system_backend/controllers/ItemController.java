package com.accounting_system.accounting_system_backend.controllers;

import com.accounting_system.accounting_system_backend.dto.requests.RegisterItemRequest;
import com.accounting_system.accounting_system_backend.dto.responses.ItemResponse;
import com.accounting_system.accounting_system_backend.exceptions.items.ItemAlreadyExistsException;
import com.accounting_system.accounting_system_backend.services.ItemService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/items")
public class ItemController {
    private final ItemService itemService;

    //@AllowedPermissions(permissionCodes = PermissionCode.ITEM_VISUALIZATION)
    @GetMapping
    public List<ItemResponse> getItems() {
        return itemService.getItems();
    }

    //@AllowedPermissions(permissionCodes = PermissionCode.ITEM_REGISTRATION)
    @PostMapping
    public ItemResponse RegisterItem(@Valid @RequestBody RegisterItemRequest request)
            throws ItemAlreadyExistsException {
        return itemService.RegisterItem(request);
    }
}
