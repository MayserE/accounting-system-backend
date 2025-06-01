package com.accounting_system.accounting_system_backend.controllers;

import com.accounting_system.accounting_system_backend.config.annotations.AllowedPermissions;
import com.accounting_system.accounting_system_backend.config.annotations.SuperAdminRequired;
import com.accounting_system.accounting_system_backend.domain.entities.enums.PermissionCode;
import com.accounting_system.accounting_system_backend.dto.requests.RegisterCompanyUserRequest;
import com.accounting_system.accounting_system_backend.dto.requests.RegisterSuperAdminUserRequest;
import com.accounting_system.accounting_system_backend.dto.responses.AuthenticatedUserResponse;
import com.accounting_system.accounting_system_backend.dto.responses.CompanyUserResponse;
import com.accounting_system.accounting_system_backend.dto.responses.UserResponse;
import com.accounting_system.accounting_system_backend.exceptions.companies.CompanyNotAssociatedException;
import com.accounting_system.accounting_system_backend.exceptions.companies.CompanyNotFoundException;
import com.accounting_system.accounting_system_backend.exceptions.roles.RoleNotFoundException;
import com.accounting_system.accounting_system_backend.exceptions.users.UserAlreadyExistsException;
import com.accounting_system.accounting_system_backend.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("api/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/authenticated")
    public AuthenticatedUserResponse getAuthenticatedUser() {
        return userService.getAuthenticatedUser();
    }

    @SuperAdminRequired
    @GetMapping("/super-admin")
    public List<UserResponse> getSuperAdminUsers() {
        return userService.getSuperAdminUsers();
    }

    @SuperAdminRequired
    @PostMapping("/super-admin")
    public UserResponse registerSuperAdminUser(@Valid @RequestBody RegisterSuperAdminUserRequest request)
            throws UserAlreadyExistsException {
        return userService.registerSuperAdminUser(request);
    }

    @AllowedPermissions(permissionCodes = PermissionCode.USER_VISUALIZATION)
    @GetMapping("/{companyId}/company")
    public List<CompanyUserResponse> getCompanyUsers(@PathVariable UUID companyId)
            throws CompanyNotAssociatedException, CompanyNotFoundException {
        return userService.getCompanyUsers(companyId);
    }

    @AllowedPermissions(permissionCodes = PermissionCode.USER_REGISTRATION)
    @PostMapping("/company")
    public CompanyUserResponse registerCompanyUser(@Valid @RequestBody RegisterCompanyUserRequest request)
            throws CompanyNotAssociatedException, CompanyNotFoundException, RoleNotFoundException,
            UserAlreadyExistsException {
        return userService.registerCompanyUser(request);
    }
}
