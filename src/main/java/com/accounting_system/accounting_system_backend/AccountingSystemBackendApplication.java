package com.accounting_system.accounting_system_backend;

import com.accounting_system.accounting_system_backend.config.AppConfig;
import com.josemayser.jwt_manager.core.JwtManager;
import com.josemayser.jwt_manager.exceptions.keys.KeysException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class AccountingSystemBackendApplication {
    private final PasswordEncoder passwordEncoder;

    public AccountingSystemBackendApplication(AppConfig appConfig, PasswordEncoder passwordEncoder) throws KeysException {
        JwtManager.getInstance().initialize(appConfig.getPrivateKeyPath(), appConfig.getPublicKeyPath(), Boolean.TRUE);
        this.passwordEncoder = passwordEncoder;
        System.out.println(passwordEncoder.encode("12345678"));
    }

    public static void main(String[] args) {
        SpringApplication.run(AccountingSystemBackendApplication.class, args);
    }
}
