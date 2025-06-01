package com.accounting_system.accounting_system_backend.config;

import com.accounting_system.accounting_system_backend.config.annotations.AllowedPermissions;
import com.accounting_system.accounting_system_backend.domain.entities.RolePermission;
import com.accounting_system.accounting_system_backend.domain.entities.enums.PermissionCode;
import com.accounting_system.accounting_system_backend.domain.repositories.RolePermissionRepository;
import com.accounting_system.accounting_system_backend.dto.responses.AuthenticatedUserResponse;
import com.accounting_system.accounting_system_backend.exceptions.ForbiddenException;
import lombok.AllArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Aspect
@Component
@AllArgsConstructor
public class RequiredPermissionsAspect {
    private final RolePermissionRepository rolePermissionRepository;

    @Before("@annotation(allowedPermissions)")
    public void verifyPermissions(AllowedPermissions allowedPermissions) throws ForbiddenException {
        AuthenticatedUserResponse authenticatedUser = SecurityContextHolder.getAuthenticatedUser();
        if (allowedPermissions.isSuperAdminAllowed() && authenticatedUser.getUser().getIsSuperAdmin()) {
            return;
        }
        PermissionCode[] permissionCodes = allowedPermissions.permissionCodes();
        if (permissionCodes == null || permissionCodes.length == 0) {
            // TODO : LANZAR UNA EXCEPCION
            return;
        }
        for (PermissionCode permissionCode : permissionCodes) {
            Optional<RolePermission> rolePermissionOptional = rolePermissionRepository.findByRoleIdAndPermissionCode(
                    authenticatedUser.getCompanyUser().getRoleId(), permissionCode);
            if (rolePermissionOptional.isPresent()) {
                return;
            }
        }
        throw new ForbiddenException("El usuario no tiene acceso al recurso.");
    }
}
