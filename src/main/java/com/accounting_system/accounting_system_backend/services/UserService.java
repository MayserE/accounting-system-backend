package com.accounting_system.accounting_system_backend.services;

import com.accounting_system.accounting_system_backend.dto.requests.RegisterCompanyUserRequest;
import com.accounting_system.accounting_system_backend.dto.requests.RegisterSuperAdminUserRequest;
import com.accounting_system.accounting_system_backend.dto.responses.AuthenticatedUserResponse;
import com.accounting_system.accounting_system_backend.dto.responses.CompanyUserResponse;
import com.accounting_system.accounting_system_backend.dto.responses.UserResponse;
import com.accounting_system.accounting_system_backend.exceptions.companies.CompanyNotAssociatedException;
import com.accounting_system.accounting_system_backend.exceptions.companies.CompanyNotFoundException;
import com.accounting_system.accounting_system_backend.exceptions.roles.RoleNotFoundException;
import com.accounting_system.accounting_system_backend.exceptions.users.UserAlreadyExistsException;

import java.util.List;
import java.util.UUID;

public interface UserService {
    AuthenticatedUserResponse getAuthenticatedUser();

    List<UserResponse> getSuperAdminUsers();

    UserResponse registerSuperAdminUser(RegisterSuperAdminUserRequest request) throws UserAlreadyExistsException;

    List<CompanyUserResponse> getCompanyUsers(UUID companyId)
            throws CompanyNotFoundException, CompanyNotAssociatedException;

    CompanyUserResponse registerCompanyUser(RegisterCompanyUserRequest request)
            throws UserAlreadyExistsException, CompanyNotFoundException, CompanyNotAssociatedException,
            RoleNotFoundException;
}
