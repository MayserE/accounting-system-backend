package com.accounting_system.accounting_system_backend.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class RegisterRoleRequest {
    @NotNull(message = "El id de la empresa es requerido")
    private UUID companyId;

    @NotBlank(message = "El nombre del rol es requerido")
    private String name;

    @NotEmpty(message = "Los permisos son requeridos")
    private List<UUID> permissionIds;
}
