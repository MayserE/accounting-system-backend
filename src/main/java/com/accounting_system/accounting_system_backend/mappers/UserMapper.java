package com.accounting_system.accounting_system_backend.mappers;

import com.accounting_system.accounting_system_backend.dto.responses.UserResponse;
import com.accounting_system.accounting_system_backend.entities.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    UserResponse toResponse(User user);
}
