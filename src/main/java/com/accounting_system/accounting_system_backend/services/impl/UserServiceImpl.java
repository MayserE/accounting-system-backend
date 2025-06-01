package com.accounting_system.accounting_system_backend.services.impl;

import com.accounting_system.accounting_system_backend.config.SecurityContextHolder;
import com.accounting_system.accounting_system_backend.domain.entities.Company;
import com.accounting_system.accounting_system_backend.domain.entities.CompanyUser;
import com.accounting_system.accounting_system_backend.domain.entities.Role;
import com.accounting_system.accounting_system_backend.domain.entities.User;
import com.accounting_system.accounting_system_backend.domain.entities.enums.UserStatus;
import com.accounting_system.accounting_system_backend.domain.repositories.CompanyRepository;
import com.accounting_system.accounting_system_backend.domain.repositories.CompanyUserRepository;
import com.accounting_system.accounting_system_backend.domain.repositories.RoleRepository;
import com.accounting_system.accounting_system_backend.domain.repositories.UserRepository;
import com.accounting_system.accounting_system_backend.dto.requests.RegisterCompanyUserRequest;
import com.accounting_system.accounting_system_backend.dto.requests.RegisterSuperAdminUserRequest;
import com.accounting_system.accounting_system_backend.dto.responses.AuthenticatedUserResponse;
import com.accounting_system.accounting_system_backend.dto.responses.CompanyUserResponse;
import com.accounting_system.accounting_system_backend.dto.responses.UserResponse;
import com.accounting_system.accounting_system_backend.exceptions.companies.CompanyNotAssociatedException;
import com.accounting_system.accounting_system_backend.exceptions.companies.CompanyNotFoundException;
import com.accounting_system.accounting_system_backend.exceptions.roles.RoleNotFoundException;
import com.accounting_system.accounting_system_backend.exceptions.users.UserAlreadyExistsException;
import com.accounting_system.accounting_system_backend.mappers.CompanyUserMapper;
import com.accounting_system.accounting_system_backend.mappers.UserMapper;
import com.accounting_system.accounting_system_backend.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final CompanyRepository companyRepository;
    private final CompanyUserRepository companyUserRepository;
    private final CompanyUserMapper companyUserMapper;
    private final RoleRepository roleRepository;

    @Override
    public AuthenticatedUserResponse getAuthenticatedUser() {
        return SecurityContextHolder.getAuthenticatedUser();
    }

    @Override
    public List<UserResponse> getSuperAdminUsers() {
        List<User> superAdminUsers = userRepository.findAllByIsSuperAdminTrue();
        return userMapper.toResponses(superAdminUsers);
    }

    @Override
    public UserResponse registerSuperAdminUser(RegisterSuperAdminUserRequest request)
            throws UserAlreadyExistsException {
        verifyIfUserExists(request.getEmail());
        User newUser = userMapper.toEntity(request);
        newUser.setEmail(newUser.getEmail().toLowerCase());
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        newUser.setIsSuperAdmin(true);
        newUser.setStatus(UserStatus.ACTIVE);
        newUser.setFirstName(newUser.getFirstName().toUpperCase());
        newUser.setLastName(newUser.getLastName().toUpperCase());
        User savedUser = userRepository.saveAndFlush(newUser);
        return userMapper.toResponse(savedUser);
    }

    @Override
    public List<CompanyUserResponse> getCompanyUsers(UUID companyId)
            throws CompanyNotFoundException, CompanyNotAssociatedException {
        Company company = getCompanyById(companyId);
        verifyIfUserAssociatedToCompany(company.getId());
        List<CompanyUser> companyUsers = companyUserRepository.findAllByCompanyId(company.getId());
        return companyUserMapper.toResponses(companyUsers);
    }

    @Override
    public CompanyUserResponse registerCompanyUser(RegisterCompanyUserRequest request)
            throws UserAlreadyExistsException, CompanyNotFoundException, CompanyNotAssociatedException,
            RoleNotFoundException {
        verifyIfUserExists(request.getEmail());
        Company company = getCompanyById(request.getCompanyId());
        verifyIfUserAssociatedToCompany(company.getId());
        Role role = roleRepository.findById(request.getRoleId())
                .orElseThrow(() -> new RoleNotFoundException("El rol no existe."));
        User user = new User();
        user.setEmail(request.getEmail().toLowerCase());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setIsSuperAdmin(false);
        user.setStatus(UserStatus.ACTIVE);
        user.setFirstName(request.getFirstName().toUpperCase());
        user.setLastName(request.getLastName().toUpperCase());
        user.setPhoneNumber(request.getPhoneNumber());
        User savedUser = userRepository.saveAndFlush(user);
        CompanyUser newCompanyUser = companyUserMapper.toEntity(request);
        newCompanyUser.setUserId(savedUser.getId());
        newCompanyUser.setCompany(company);
        newCompanyUser.setUser(savedUser);
        newCompanyUser.setRole(role);
        CompanyUser savedCompanyUser = companyUserRepository.saveAndFlush(newCompanyUser);
        return companyUserMapper.toResponse(savedCompanyUser);
    }

    private void verifyIfUserExists(String email) throws UserAlreadyExistsException {
        Optional<User> userOptional = userRepository.findByEmail(email.toLowerCase());
        if (userOptional.isPresent()) {
            throw new UserAlreadyExistsException(String.format("Ya existe un usuario con el email: %s.", email));
        }
    }

    private Company getCompanyById(UUID companyId) throws CompanyNotFoundException {
        return companyRepository.findById(companyId)
                .orElseThrow(() -> new CompanyNotFoundException("La empresa no existe."));
    }

    private void verifyIfUserAssociatedToCompany(UUID companyId) throws CompanyNotAssociatedException {
        AuthenticatedUserResponse authenticatedUser = SecurityContextHolder.getAuthenticatedUser();
        if (!authenticatedUser.getUser().getIsSuperAdmin() &&
                !authenticatedUser.getCompanyUser().getCompanyId().equals(companyId)) {
            throw new CompanyNotAssociatedException("Usuario no asociado a la empresa.");
        }
    }
}
