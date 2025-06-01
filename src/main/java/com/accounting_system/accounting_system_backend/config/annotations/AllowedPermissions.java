package com.accounting_system.accounting_system_backend.config.annotations;

import com.accounting_system.accounting_system_backend.domain.entities.enums.PermissionCode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface AllowedPermissions {
    PermissionCode[] permissionCodes() default {};

    boolean isSuperAdminAllowed() default true;
}
