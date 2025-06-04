package com.accounting_system.accounting_system_backend.dto.responses;

import com.accounting_system.accounting_system_backend.domain.entities.enums.AccountStatus;
import com.accounting_system.accounting_system_backend.domain.entities.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class AccountResponse {
    private UUID id;
    private UUID companyId;
    private UUID parentAccountId;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private AccountStatus status;
    private String name;
    private AccountType type;
}
