package com.accounting_system.accounting_system_backend.dto.responses;

import com.accounting_system.accounting_system_backend.domain.entities.enums.PermissionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class PermissionResponse {
    private UUID id;
    private PermissionCode code;
    private String description;
}
