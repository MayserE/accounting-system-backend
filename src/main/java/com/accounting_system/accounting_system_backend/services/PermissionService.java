package com.accounting_system.accounting_system_backend.services;

import com.accounting_system.accounting_system_backend.dto.responses.PermissionResponse;

import java.util.List;

public interface PermissionService {
    List<PermissionResponse> getPermissions();
}
