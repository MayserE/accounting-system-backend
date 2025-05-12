package com.accounting_system.accounting_system_backend.dto.responses;

import com.accounting_system.accounting_system_backend.enums.RoleStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class RoleResponse {
    private UUID id;
    private UUID companyId;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String name;
    private RoleStatus status;
    private CompanyResponse company;
}
