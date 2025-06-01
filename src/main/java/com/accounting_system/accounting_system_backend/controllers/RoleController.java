package com.accounting_system.accounting_system_backend.controllers;

import com.accounting_system.accounting_system_backend.config.annotations.AllowedPermissions;
import com.accounting_system.accounting_system_backend.domain.entities.enums.PermissionCode;
import com.accounting_system.accounting_system_backend.dto.requests.RegisterRoleRequest;
import com.accounting_system.accounting_system_backend.dto.responses.RoleWithPermissionsResponse;
import com.accounting_system.accounting_system_backend.exceptions.companies.CompanyNotAssociatedException;
import com.accounting_system.accounting_system_backend.exceptions.companies.CompanyNotFoundException;
import com.accounting_system.accounting_system_backend.exceptions.permissions.PermissionNotFoundException;
import com.accounting_system.accounting_system_backend.exceptions.roles.RoleAlreadyExistsException;
import com.accounting_system.accounting_system_backend.services.RoleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("api/roles")
public class RoleController {
    private final RoleService roleService;

    @AllowedPermissions(permissionCodes = {PermissionCode.ROLE_VISUALIZATION, PermissionCode.USER_REGISTRATION})
    @GetMapping("/{companyId}")
    public List<RoleWithPermissionsResponse> getRolesByCompanyId(@PathVariable UUID companyId)
            throws CompanyNotAssociatedException, CompanyNotFoundException {
        return roleService.getRolesByCompanyId(companyId);
    }

    @AllowedPermissions(permissionCodes = PermissionCode.ROLE_REGISTRATION)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoleWithPermissionsResponse registerRole(@Valid @RequestBody RegisterRoleRequest request)
            throws CompanyNotAssociatedException, CompanyNotFoundException, RoleAlreadyExistsException,
            PermissionNotFoundException {
        return roleService.registerRole(request);
    }
}
