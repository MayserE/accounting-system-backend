package com.accounting_system.accounting_system_backend.mappers;

import com.accounting_system.accounting_system_backend.domain.entities.Item;
import com.accounting_system.accounting_system_backend.dto.requests.RegisterItemRequest;
import com.accounting_system.accounting_system_backend.dto.responses.ItemResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ItemMapper {
    List<ItemResponse> toResponses(List<Item> items);

    Item toEntity(RegisterItemRequest request);

    ItemResponse toResponse(Item item);
}
