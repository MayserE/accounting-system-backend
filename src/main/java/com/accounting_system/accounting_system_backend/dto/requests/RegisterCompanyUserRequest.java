package com.accounting_system.accounting_system_backend.dto.requests;

import com.accounting_system.accounting_system_backend.domain.entities.enums.CompanyUserStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.UUID;

@Getter
public class RegisterCompanyUserRequest {
    @NotBlank(message = "El email es requerido")
    private String email;

    @NotBlank(message = "La contraseña es requerida")
    private String password;

    @NotBlank(message = "El nombre es requerido")
    private String firstName;

    @NotBlank(message = "El apellido es requerido")
    private String lastName;

    private String phoneNumber;

    @NotNull(message = "La empresa es requerida")
    private UUID companyId;

    @NotNull(message = "El rol es requerido")
    private UUID roleId;

    @NotNull(message = "El estado es requerido")
    private CompanyUserStatus status;
}
