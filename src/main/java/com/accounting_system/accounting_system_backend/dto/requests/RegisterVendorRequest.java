package com.accounting_system.accounting_system_backend.dto.requests;

import com.accounting_system.accounting_system_backend.domain.entities.enums.VendorStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class RegisterVendorRequest {
    @NotNull(message = "El estado es requerido")
    private VendorStatus status;

    @NotBlank(message = "El nombre es requerido")
    private String name;

    @NotBlank(message = "El email es requerido")
    private String email;

    private String phoneNumber;
}
