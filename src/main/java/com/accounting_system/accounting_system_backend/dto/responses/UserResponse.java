package com.accounting_system.accounting_system_backend.dto.responses;

import com.accounting_system.accounting_system_backend.domain.entities.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class UserResponse {
    private UUID id;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String email;
    private Boolean isSuperAdmin;
    private UserStatus status;
    private String firstName;
    private String lastName;
    private String phoneNumber;
}
