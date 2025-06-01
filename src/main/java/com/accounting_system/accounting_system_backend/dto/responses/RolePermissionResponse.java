package com.accounting_system.accounting_system_backend.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class RolePermissionResponse {
    private UUID id;
    private UUID roleId;
    private UUID permissionId;
    private Timestamp createdAt;
    private PermissionResponse permission;
}
