package com.accounting_system.accounting_system_backend.services.impl;

import com.accounting_system.accounting_system_backend.config.SecurityContextHolder;
import com.accounting_system.accounting_system_backend.domain.entities.Company;
import com.accounting_system.accounting_system_backend.domain.entities.Permission;
import com.accounting_system.accounting_system_backend.domain.entities.Role;
import com.accounting_system.accounting_system_backend.domain.entities.RolePermission;
import com.accounting_system.accounting_system_backend.domain.entities.enums.RoleStatus;
import com.accounting_system.accounting_system_backend.domain.repositories.CompanyRepository;
import com.accounting_system.accounting_system_backend.domain.repositories.PermissionRepository;
import com.accounting_system.accounting_system_backend.domain.repositories.RolePermissionRepository;
import com.accounting_system.accounting_system_backend.domain.repositories.RoleRepository;
import com.accounting_system.accounting_system_backend.dto.requests.RegisterRoleRequest;
import com.accounting_system.accounting_system_backend.dto.responses.AuthenticatedUserResponse;
import com.accounting_system.accounting_system_backend.dto.responses.RoleWithPermissionsResponse;
import com.accounting_system.accounting_system_backend.exceptions.companies.CompanyNotAssociatedException;
import com.accounting_system.accounting_system_backend.exceptions.companies.CompanyNotFoundException;
import com.accounting_system.accounting_system_backend.exceptions.permissions.PermissionNotFoundException;
import com.accounting_system.accounting_system_backend.exceptions.roles.RoleAlreadyExistsException;
import com.accounting_system.accounting_system_backend.mappers.RoleMapper;
import com.accounting_system.accounting_system_backend.mappers.RolePermissionMapper;
import com.accounting_system.accounting_system_backend.services.RoleService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final CompanyRepository companyRepository;
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    private final PermissionRepository permissionRepository;
    private final RolePermissionRepository rolePermissionRepository;
    private final RolePermissionMapper rolePermissionMapper;

    @Override
    public List<RoleWithPermissionsResponse> getRolesByCompanyId(UUID companyId)
            throws CompanyNotFoundException, CompanyNotAssociatedException {
        Company company = getCompanyById(companyId);
        AuthenticatedUserResponse authenticatedUser = SecurityContextHolder.getAuthenticatedUser();
        if (!authenticatedUser.getUser().getIsSuperAdmin() &&
                !authenticatedUser.getCompanyUser().getCompanyId().equals(company.getId())) {
            throw new CompanyNotAssociatedException("Usuario no asociado a la empresa.");
        }
        List<Role> roles = roleRepository.findAllByCompanyId(company.getId());
        List<RoleWithPermissionsResponse> rolesWithPermissions = new ArrayList<>();
        for (Role role : roles) {
            List<RolePermission> rolePermissions = rolePermissionRepository.findAllByRoleId(role.getId());
            RoleWithPermissionsResponse roleWithPermissions = new RoleWithPermissionsResponse(
                    roleMapper.toResponse(role), rolePermissionMapper.toResponses(rolePermissions)
            );
            rolesWithPermissions.add(roleWithPermissions);
        }
        return rolesWithPermissions;
    }

    @Transactional(rollbackOn = PermissionNotFoundException.class)
    @Override
    public RoleWithPermissionsResponse registerRole(RegisterRoleRequest request)
            throws CompanyNotFoundException, CompanyNotAssociatedException, RoleAlreadyExistsException,
            PermissionNotFoundException {
        Company company = getCompanyById(request.getCompanyId());
        AuthenticatedUserResponse authenticatedUser = SecurityContextHolder.getAuthenticatedUser();
        if (!authenticatedUser.getUser().getIsSuperAdmin() &&
                !authenticatedUser.getCompanyUser().getCompanyId().equals(company.getId())) {
            throw new CompanyNotAssociatedException("Usuario no asociado a la empresa.");
        }
        Optional<Role> roleOptional =
                roleRepository.findByCompanyIdAndName(company.getId(), request.getName().toUpperCase());
        if (roleOptional.isPresent()) {
            throw new RoleAlreadyExistsException(
                    String.format("Ya existe un rol con el nombre: %s.", request.getName())
            );
        }
        Role role = roleMapper.toEntity(request);
        role.setName(role.getName().toUpperCase());
        role.setStatus(RoleStatus.ACTIVE);
        Role savedRole = roleRepository.saveAndFlush(role);
        List<RolePermission> rolePermissions = new ArrayList<>();
        for (UUID permissionId : request.getPermissionIds()) {
            Permission permission = permissionRepository.findById(permissionId).orElseThrow(
                    () -> new PermissionNotFoundException(
                            String.format("No existe el permiso con el id: %s.", permissionId)
                    )
            );
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(savedRole.getId());
            rolePermission.setPermissionId(permission.getId());
            rolePermission.setPermission(permission);
            rolePermissions.add(rolePermission);
        }
        List<RolePermission> savedRolePermissions = rolePermissionRepository.saveAllAndFlush(rolePermissions);
        return new RoleWithPermissionsResponse(
                roleMapper.toResponse(savedRole),
                rolePermissionMapper.toResponses(savedRolePermissions)
        );
    }

    private Company getCompanyById(UUID id) throws CompanyNotFoundException {
        return companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException("La empresa no existe."));
    }
}
