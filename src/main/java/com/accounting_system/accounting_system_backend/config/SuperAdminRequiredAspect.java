package com.accounting_system.accounting_system_backend.config;

import com.accounting_system.accounting_system_backend.config.annotations.SuperAdminRequired;
import com.accounting_system.accounting_system_backend.dto.responses.AuthenticatedUserResponse;
import com.accounting_system.accounting_system_backend.exceptions.ForbiddenException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SuperAdminRequiredAspect {
    @Before("@annotation(superAdminRequired)")
    public void verifySuperAdmin(SuperAdminRequired superAdminRequired) throws ForbiddenException {
        System.out.println("Entrando a la funcion SuperAdminRequired");
        AuthenticatedUserResponse user = SecurityContextHolder.getAuthenticatedUser();
        if (!user.getUser().getIsSuperAdmin()) {
            throw new ForbiddenException("Acceso denegado al recurso.");
        }
    }
}
