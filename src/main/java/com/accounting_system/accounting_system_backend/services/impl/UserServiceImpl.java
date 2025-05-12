package com.accounting_system.accounting_system_backend.services.impl;

import com.accounting_system.accounting_system_backend.config.SecurityContextHolder;
import com.accounting_system.accounting_system_backend.dto.responses.AuthenticatedUserResponse;
import com.accounting_system.accounting_system_backend.entities.CompanyUser;
import com.accounting_system.accounting_system_backend.entities.User;
import com.accounting_system.accounting_system_backend.exceptions.company_users.CompanyUserNotFoundException;
import com.accounting_system.accounting_system_backend.mappers.CompanyUserMapper;
import com.accounting_system.accounting_system_backend.mappers.UserMapper;
import com.accounting_system.accounting_system_backend.repositories.CompanyUserRepository;
import com.accounting_system.accounting_system_backend.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private CompanyUserRepository companyUserRepository;
    private UserMapper userMapper;
    private CompanyUserMapper companyUserMapper;

    @Override
    public AuthenticatedUserResponse getAuthenticatedUser() throws CompanyUserNotFoundException {
        User user = SecurityContextHolder.getCurrentUser();
        AuthenticatedUserResponse response = new AuthenticatedUserResponse();
        response.setUser(userMapper.toResponse(user));
        if (!user.getIsSuperAdmin()) {
            CompanyUser companyUser = companyUserRepository.findFirstByUserIdOrderByCreatedAtDesc(user.getId()).orElseThrow(() -> new CompanyUserNotFoundException("Usuario de empresa no encontrado."));
            response.setCompanyUser(companyUserMapper.toResponse(companyUser));
        }
        return response;
    }
}
