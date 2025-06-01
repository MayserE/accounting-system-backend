package com.accounting_system.accounting_system_backend.domain.entities;

import com.accounting_system.accounting_system_backend.domain.entities.enums.PermissionCode;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "permissions")
@Getter
@Setter
public class Permission {
    @Id
    @GeneratedValue
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 128)
    private PermissionCode code;

    @Column(nullable = false, length = 128)
    private String description;
}
