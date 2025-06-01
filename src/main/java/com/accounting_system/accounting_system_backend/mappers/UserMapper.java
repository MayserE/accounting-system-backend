package com.accounting_system.accounting_system_backend.mappers;

import com.accounting_system.accounting_system_backend.domain.entities.User;
import com.accounting_system.accounting_system_backend.dto.requests.RegisterSuperAdminUserRequest;
import com.accounting_system.accounting_system_backend.dto.responses.UserResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    UserResponse toResponse(User user);

    User toEntity(RegisterSuperAdminUserRequest user);

    List<UserResponse> toResponses(List<User> users);
}
