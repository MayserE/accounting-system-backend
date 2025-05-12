package com.accounting_system.accounting_system_backend.config;

import com.accounting_system.accounting_system_backend.dto.responses.ErrorResponse;
import com.accounting_system.accounting_system_backend.entities.User;
import com.accounting_system.accounting_system_backend.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.josemayser.jwt_manager.core.JwtManager;
import com.josemayser.jwt_manager.exceptions.JwtDataException;
import com.josemayser.jwt_manager.exceptions.JwtValidationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@Component
public class SecurityFilter extends OncePerRequestFilter {
    private final UserRepository userRepository;

    private static final List<String> unprotectedUrls = List.of("/api/auth/login");

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
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
                SecurityContextHolder.setCurrentUser(foundUser);
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

    private void writeUnauthorizedResponse(String message, String requestUri, HttpServletResponse response) throws IOException {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        ErrorResponse error = new ErrorResponse(status, message, requestUri);
        String responseBody = new ObjectMapper().writeValueAsString(error);
        response.getWriter().write(responseBody);
        response.setContentType("application/json");
        response.setStatus(status.value());
    }
}
