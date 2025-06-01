package com.accounting_system.accounting_system_backend.dto.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class RegisterCompanyRequest {
    @NotBlank(message = "El nombre es requerido")
    private String name;

    @NotBlank(message = "La identificación fiscal es requerida")
    private String taxId;
    private String address;
    private String phoneNumber;
}
