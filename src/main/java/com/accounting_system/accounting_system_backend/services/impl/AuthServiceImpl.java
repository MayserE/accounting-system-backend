package com.accounting_system.accounting_system_backend.services.impl;

import com.accounting_system.accounting_system_backend.dto.responses.AuthResponse;
import com.accounting_system.accounting_system_backend.entities.CompanyUser;
import com.accounting_system.accounting_system_backend.entities.User;
import com.accounting_system.accounting_system_backend.enums.CompanyUserStatus;
import com.accounting_system.accounting_system_backend.enums.UserStatus;
import com.accounting_system.accounting_system_backend.exceptions.auth.InactiveUserException;
import com.accounting_system.accounting_system_backend.exceptions.auth.InvalidCredentialsException;
import com.accounting_system.accounting_system_backend.exceptions.auth.TokenGenerationException;
import com.accounting_system.accounting_system_backend.repositories.CompanyUserRepository;
import com.accounting_system.accounting_system_backend.repositories.UserRepository;
import com.accounting_system.accounting_system_backend.services.AuthService;
import com.josemayser.jwt_manager.core.JwtManager;
import com.josemayser.jwt_manager.domain.ExpirationTimeType;
import com.josemayser.jwt_manager.domain.JwtRequest;
import com.josemayser.jwt_manager.domain.JwtResponse;
import com.josemayser.jwt_manager.exceptions.JwtGenerationException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CompanyUserRepository companyUserRepository;

    @Override
    public AuthResponse logIn(String email, String password) throws InvalidCredentialsException, InactiveUserException, TokenGenerationException {
        User user = userRepository.findByEmail(email).orElseThrow(InvalidCredentialsException::new);
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidCredentialsException();
        }
        if (user.getStatus() == UserStatus.INACTIVE) {
            throw new InactiveUserException();
        }
        if (!user.getIsSuperAdmin()) {
            CompanyUser companyUser = companyUserRepository.findFirstByUserIdOrderByCreatedAtDesc(user.getId()).orElseThrow(InvalidCredentialsException::new);
            if (companyUser.getStatus() == CompanyUserStatus.INACTIVE) {
                throw new InactiveUserException();
            }
        }
        JwtRequest<User> jwtRequest = new JwtRequest<>("accounting-system", user.getEmail(), ExpirationTimeType.DAY, 7, user);
        try {
            JwtResponse jwtResponse = JwtManager.getInstance().generateJwt(jwtRequest);
            return new AuthResponse(jwtResponse.getToken(), new Timestamp(jwtResponse.getExpiresAt().getTime()));
        } catch (JwtGenerationException e) {
            throw new TokenGenerationException(e);
        }
    }
}
