package com.accounting_system.accounting_system_backend.services.impl;

import com.accounting_system.accounting_system_backend.config.SecurityContextHolder;
import com.accounting_system.accounting_system_backend.domain.entities.Item;
import com.accounting_system.accounting_system_backend.domain.repositories.ItemRepository;
import com.accounting_system.accounting_system_backend.dto.requests.RegisterItemRequest;
import com.accounting_system.accounting_system_backend.dto.responses.AuthenticatedUserResponse;
import com.accounting_system.accounting_system_backend.dto.responses.ItemResponse;
import com.accounting_system.accounting_system_backend.exceptions.items.ItemAlreadyExistsException;
import com.accounting_system.accounting_system_backend.mappers.ItemMapper;
import com.accounting_system.accounting_system_backend.services.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    @Override
    public List<ItemResponse> getItems() {
        UUID companyId = getCompanyIdFromAuthenticatedUser();
        List<Item> items = itemRepository.findAllByCompanyId(companyId);
        return itemMapper.toResponses(items);
    }

    @Override
    public ItemResponse RegisterItem(RegisterItemRequest request) throws ItemAlreadyExistsException {
        UUID companyId = getCompanyIdFromAuthenticatedUser();
        String name = request.getName();
        Optional<Item> itemOptional = itemRepository.findByCompanyIdAndName(companyId, name.toUpperCase());
        if (itemOptional.isPresent()) {
            throw new ItemAlreadyExistsException(String.format("Ya existe un ítem con el nombre: %s.", name));
        }
        Item newItem = itemMapper.toEntity(request);
        newItem.setCompanyId(companyId);
        newItem.setName(newItem.getName().toUpperCase());
        Item savedItem = itemRepository.saveAndFlush(newItem);
        return itemMapper.toResponse(savedItem);
    }

    private UUID getCompanyIdFromAuthenticatedUser() {
        AuthenticatedUserResponse authenticatedUser = SecurityContextHolder.getAuthenticatedUser();
        return authenticatedUser.getCompanyUser().getCompanyId();
    }
}
