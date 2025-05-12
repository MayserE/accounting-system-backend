package com.accounting_system.accounting_system_backend.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@AllArgsConstructor
@Getter
@Setter
public class AuthResponse {
    private String token;
    private Timestamp expiresAt;
}
