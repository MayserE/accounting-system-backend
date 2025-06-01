package com.accounting_system.accounting_system_backend.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class RoleWithPermissionsResponse {
    private RoleResponse role;
    private List<RolePermissionResponse> rolePermissions;
}
