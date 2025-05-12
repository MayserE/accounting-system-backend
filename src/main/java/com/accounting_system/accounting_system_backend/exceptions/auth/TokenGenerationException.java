package com.accounting_system.accounting_system_backend.exceptions.auth;

public class TokenGenerationException extends Exception {
    public TokenGenerationException(Throwable cause) {
        super("Error al generar el token de autenticación.", cause);
    }
}
