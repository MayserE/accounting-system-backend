package com.accounting_system.accounting_system_backend.dto.requests;

import com.accounting_system.accounting_system_backend.domain.entities.enums.ItemStatus;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class RegisterItemRequest {
    @NotNull(message = "El estado es requerido")
    private ItemStatus status;

    @NotBlank(message = "El nombre es requerido")
    private String name;

    private String description;

    @DecimalMin(value = "0.0", message = "El precio no debe ser menor a 0")
    private BigDecimal price;
}
