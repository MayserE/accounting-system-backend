package com.accounting_system.accounting_system_backend.services;

import com.accounting_system.accounting_system_backend.dto.requests.RegisterRoleRequest;
import com.accounting_system.accounting_system_backend.dto.responses.RoleWithPermissionsResponse;
import com.accounting_system.accounting_system_backend.exceptions.companies.CompanyNotAssociatedException;
import com.accounting_system.accounting_system_backend.exceptions.companies.CompanyNotFoundException;
import com.accounting_system.accounting_system_backend.exceptions.permissions.PermissionNotFoundException;
import com.accounting_system.accounting_system_backend.exceptions.roles.RoleAlreadyExistsException;

import java.util.List;
import java.util.UUID;

public interface RoleService {
    List<RoleWithPermissionsResponse> getRolesByCompanyId(UUID companyId)
            throws CompanyNotFoundException, CompanyNotAssociatedException;

    RoleWithPermissionsResponse registerRole(RegisterRoleRequest request)
            throws CompanyNotFoundException, CompanyNotAssociatedException, RoleAlreadyExistsException,
            PermissionNotFoundException;
}
