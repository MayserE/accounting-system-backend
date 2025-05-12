package com.accounting_system.accounting_system_backend.dto.responses;

import com.accounting_system.accounting_system_backend.enums.CompanyStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class CompanyResponse {
    private UUID id;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private CompanyStatus status;
    private String name;
    private String taxId;
    private String address;
    private String phoneNumber;
}
