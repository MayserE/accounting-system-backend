package com.accounting_system.accounting_system_backend.services;

import com.accounting_system.accounting_system_backend.dto.responses.AuthResponse;
import com.accounting_system.accounting_system_backend.exceptions.auth.InactiveUserException;
import com.accounting_system.accounting_system_backend.exceptions.auth.InvalidCredentialsException;
import com.accounting_system.accounting_system_backend.exceptions.auth.TokenGenerationException;

public interface AuthService {
    AuthResponse logIn(String email, String password) throws InvalidCredentialsException, InactiveUserException, TokenGenerationException;
}
