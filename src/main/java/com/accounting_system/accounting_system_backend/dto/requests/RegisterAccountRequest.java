package com.accounting_system.accounting_system_backend.dto.requests;

import com.accounting_system.accounting_system_backend.domain.entities.enums.AccountStatus;
import com.accounting_system.accounting_system_backend.domain.entities.enums.AccountType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.UUID;

@Getter
public class RegisterAccountRequest {
    private UUID parentAccountId;

    @NotNull(message = "El estado es requerido")
    private AccountStatus status;

    @NotBlank(message = "El nombre es requerido")
    private String name;

    private AccountType type;
}
