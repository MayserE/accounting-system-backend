package com.accounting_system.accounting_system_backend.services;

import com.accounting_system.accounting_system_backend.dto.responses.AuthenticatedUserResponse;
import com.accounting_system.accounting_system_backend.entities.User;
import com.accounting_system.accounting_system_backend.exceptions.company_users.CompanyUserNotFoundException;

public interface UserService {
    AuthenticatedUserResponse getAuthenticatedUser() throws CompanyUserNotFoundException;
}
