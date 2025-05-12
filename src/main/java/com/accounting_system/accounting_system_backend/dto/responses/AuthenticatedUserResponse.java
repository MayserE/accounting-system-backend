package com.accounting_system.accounting_system_backend.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthenticatedUserResponse {
    private UserResponse user;
    private CompanyUserResponse companyUser;
}
