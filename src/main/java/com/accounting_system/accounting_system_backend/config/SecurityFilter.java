package com.accounting_system.accounting_system_backend.config;

import com.accounting_system.accounting_system_backend.domain.entities.Company;
import com.accounting_system.accounting_system_backend.domain.entities.CompanyUser;
import com.accounting_system.accounting_system_backend.domain.entities.Role;
import com.accounting_system.accounting_system_backend.domain.entities.User;
import com.accounting_system.accounting_system_backend.domain.repositories.CompanyRepository;
import com.accounting_system.accounting_system_backend.domain.repositories.CompanyUserRepository;
import com.accounting_system.accounting_system_backend.domain.repositories.RoleRepository;
import com.accounting_system.accounting_system_backend.domain.repositories.UserRepository;
import com.accounting_system.accounting_system_backend.dto.responses.AuthenticatedUserResponse;
import com.accounting_system.accounting_system_backend.dto.responses.ErrorResponse;
import com.accounting_system.accounting_system_backend.dto.responses.UserResponse;
import com.accounting_system.accounting_system_backend.mappers.CompanyMapper;
import com.accounting_system.accounting_system_backend.mappers.CompanyUserMapper;
import com.accounting_system.accounting_system_backend.mappers.RoleMapper;
import com.accounting_system.accounting_system_backend.mappers.UserMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.josemayser.jwt_manager.core.JwtManager;
import com.josemayser.jwt_manager.exceptions.JwtDataException;
import com.josemayser.jwt_manager.exceptions.JwtValidationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Component
public class SecurityFilter extends OncePerRequestFilter {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final CompanyUserRepository companyUserRepository;
    private final CompanyUserMapper companyUserMapper;
    private final CompanyRepository companyRepository;
    private final RoleRepository roleRepository;
    private final CompanyMapper companyMapper;
    private final RoleMapper roleMapper;

    private static final List<String> unprotectedUrls = List.of("/api/auth/login");

    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        if (!unprotectedUrls.contains(request.getServletPath())) {
            String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
            String requestUri = request.getRequestURI();
            if (authorization == null || authorization.isBlank()) {
                writeUnauthorizedResponse("El token es requerido.", requestUri, response);
                return;
            }
            if (!authorization.startsWith("Bearer ")) {
                writeUnauthorizedResponse("El token es inválido.", requestUri, response);
                return;
            }
            String token = authorization.substring("Bearer ".length());
            JwtManager jwtManager = JwtManager.getInstance();
            try {
                if (jwtManager.jwtExpired(token)) {
                    writeUnauthorizedResponse("El token expiró.", requestUri, response);
                    return;
                }
                if (!jwtManager.jwtIntegrityIsValid(token)) {
                    writeUnauthorizedResponse("El token es inválido.", requestUri, response);
                    return;
                }
                User user = jwtManager.getDataFromJwt(token, User.class);
                User foundUser = userRepository.findById(user.getId()).orElse(null);
                if (foundUser == null) {
                    writeUnauthorizedResponse("Usuario no autorizado.", requestUri, response);
                    return;
                }
                UserResponse userResponse = userMapper.toResponse(foundUser);
                AuthenticatedUserResponse authenticatedUserResponse = new AuthenticatedUserResponse();
                authenticatedUserResponse.setUser(userResponse);
                if (!userResponse.getIsSuperAdmin()) {
                    Optional<CompanyUser> companyUserOptional =
                            companyUserRepository.findFirstByUserIdOrderByCreatedAtDesc(userResponse.getId());
                    if (companyUserOptional.isEmpty()) {
                        writeUnauthorizedResponse("Usuario no autorizado.", requestUri, response);
                        return;
                    }
                    CompanyUser companyUser = companyUserOptional.get();
                    Optional<Company> companyOptional = companyRepository.findById(companyUser.getCompanyId());
                    if (companyOptional.isEmpty()) {
                        writeUnauthorizedResponse("No se encontró la empresa del usuario.", requestUri, response);
                        return;
                    }
                    Optional<Role> roleOptional = roleRepository.findById(companyUser.getRoleId());
                    if (roleOptional.isEmpty()) {
                        writeUnauthorizedResponse("No se encontró el rol del usuario.", requestUri, response);
                        return;
                    }
                    companyUser.setCompany(companyOptional.get());
                    companyUser.setUser(null);
                    companyUser.setRole(roleOptional.get());
                    authenticatedUserResponse.setCompanyUser(companyUserMapper.toResponse(companyUser));
                }
                SecurityContextHolder.setAuthenticatedUser(authenticatedUserResponse);
            } catch (JwtValidationException e) {
                writeUnauthorizedResponse("Error al validar el token.", requestUri, response);
                return;
            } catch (JwtDataException e) {
                writeUnauthorizedResponse("El token es inválido.", requestUri, response);
                return;
            }
        }
        try {
            filterChain.doFilter(request, response);
        } finally {
            SecurityContextHolder.clear();
        }
    }

    private void writeUnauthorizedResponse(String message, String requestUri, HttpServletResponse response)
            throws IOException {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        ErrorResponse error = new ErrorResponse(status, message, requestUri);
        String responseBody = new ObjectMapper().writeValueAsString(error);
        response.getWriter().write(responseBody);
        response.setContentType("application/json");
        response.setStatus(status.value());
    }
}
