package com.accounting_system.accounting_system_backend.controllers;

import com.accounting_system.accounting_system_backend.config.annotations.AllowedPermissions;
import com.accounting_system.accounting_system_backend.domain.entities.enums.PermissionCode;
import com.accounting_system.accounting_system_backend.dto.responses.PermissionResponse;
import com.accounting_system.accounting_system_backend.services.PermissionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/permissions")
public class PermissionController {
    private final PermissionService permissionService;

    @AllowedPermissions(permissionCodes = PermissionCode.ROLE_REGISTRATION)
    @GetMapping
    public List<PermissionResponse> getPermissions() {
        return permissionService.getPermissions();
    }
}
