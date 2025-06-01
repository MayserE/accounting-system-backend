package com.accounting_system.accounting_system_backend.dto.requests;

import com.accounting_system.accounting_system_backend.domain.entities.enums.ClientDocumentType;
import com.accounting_system.accounting_system_backend.domain.entities.enums.ClientStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class RegisterClientRequest {
    @NotNull(message = "El estado es requerido")
    private ClientStatus status;

    @NotBlank(message = "El nombre es requerido")
    private String name;

    @NotNull(message = "El tipo de documento es requerido")
    private ClientDocumentType documentType;

    @NotBlank(message = "El número de documento es requerido")
    private String documentNumber;

    @NotBlank(message = "El email es requerido")
    private String email;

    private String taxId;

    private String phoneNumber;
}
