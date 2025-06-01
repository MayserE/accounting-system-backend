package com.accounting_system.accounting_system_backend.dto.responses;

import com.accounting_system.accounting_system_backend.domain.entities.enums.CompanyUserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class CompanyUserResponse {
    private UUID id;
    private UUID companyId;
    private UUID userId;
    private UUID roleId;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private CompanyUserStatus status;
    private CompanyResponse company;
    private UserResponse user;
    private RoleResponse role;
}
