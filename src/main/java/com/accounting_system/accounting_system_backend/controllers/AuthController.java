package com.accounting_system.accounting_system_backend.controllers;

import com.accounting_system.accounting_system_backend.dto.requests.LogInRequest;
import com.accounting_system.accounting_system_backend.dto.responses.AuthResponse;
import com.accounting_system.accounting_system_backend.exceptions.auth.InactiveUserException;
import com.accounting_system.accounting_system_backend.exceptions.auth.InvalidCredentialsException;
import com.accounting_system.accounting_system_backend.exceptions.auth.TokenGenerationException;
import com.accounting_system.accounting_system_backend.services.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public AuthResponse logIn(@Valid @RequestBody LogInRequest request) throws TokenGenerationException, InvalidCredentialsException, InactiveUserException {
        return authService.logIn(request.getEmail(), request.getPassword());
    }
}
