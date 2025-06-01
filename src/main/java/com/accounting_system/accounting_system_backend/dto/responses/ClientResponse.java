package com.accounting_system.accounting_system_backend.dto.responses;

import com.accounting_system.accounting_system_backend.domain.entities.enums.ClientDocumentType;
import com.accounting_system.accounting_system_backend.domain.entities.enums.ClientStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class ClientResponse {
    private UUID id;
    private UUID companyId;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private ClientStatus status;
    private String name;
    private ClientDocumentType documentType;
    private String documentNumber;
    private String email;
    private String taxId;
    private String phoneNumber;
}
