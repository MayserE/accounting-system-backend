package com.accounting_system.accounting_system_backend.mappers;

import com.accounting_system.accounting_system_backend.domain.entities.Role;
import com.accounting_system.accounting_system_backend.dto.requests.RegisterRoleRequest;
import com.accounting_system.accounting_system_backend.dto.responses.RoleResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {
    RoleResponse toResponse(Role role);

    Role toEntity(RegisterRoleRequest request);

    List<RoleResponse> toResponses(List<Role> roles);
}
