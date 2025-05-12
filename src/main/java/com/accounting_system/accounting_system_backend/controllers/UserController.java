package com.accounting_system.accounting_system_backend.controllers;

import com.accounting_system.accounting_system_backend.dto.responses.AuthenticatedUserResponse;
import com.accounting_system.accounting_system_backend.exceptions.company_users.CompanyUserNotFoundException;
import com.accounting_system.accounting_system_backend.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("api/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/authenticated")
    public AuthenticatedUserResponse getAuthenticatedUser() throws CompanyUserNotFoundException {
        return userService.getAuthenticatedUser();
    }
}
