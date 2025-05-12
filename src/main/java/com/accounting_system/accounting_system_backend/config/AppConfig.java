package com.accounting_system.accounting_system_backend.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class AppConfig {
    @Value("${key-pair.path.private-key}")
    private String privateKeyPath;

    @Value("${key-pair.path.public-key}")
    private String publicKeyPath;
}
