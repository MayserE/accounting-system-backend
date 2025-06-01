package com.accounting_system.accounting_system_backend.dto.responses;

import com.accounting_system.accounting_system_backend.domain.entities.enums.ItemStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class ItemResponse {
    private UUID id;
    private UUID companyId;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private ItemStatus status;
    private String name;
    private String description;
    private BigDecimal price;
}
