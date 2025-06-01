package com.accounting_system.accounting_system_backend.config;

import com.accounting_system.accounting_system_backend.dto.responses.AuthenticatedUserResponse;

public class SecurityContextHolder {
    private static final ThreadLocal<AuthenticatedUserResponse> currentUser = new ThreadLocal<>();

    public static AuthenticatedUserResponse getAuthenticatedUser() {
        return currentUser.get();
    }

    public static void setAuthenticatedUser(AuthenticatedUserResponse authenticatedUser) {
        currentUser.set(authenticatedUser);
    }

    public static void clear() {
        currentUser.remove();
    }
}
