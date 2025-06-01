package com.accounting_system.accounting_system_backend.dto.responses;

import com.accounting_system.accounting_system_backend.domain.entities.enums.VendorStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class VendorResponse {
    private UUID id;
    private UUID companyId;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private VendorStatus status;
    private String name;
    private String email;
    private String phoneNumber;
}
