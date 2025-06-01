package com.accounting_system.accounting_system_backend.mappers;

import com.accounting_system.accounting_system_backend.domain.entities.RolePermission;
import com.accounting_system.accounting_system_backend.dto.responses.RolePermissionResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface RolePermissionMapper {
    List<RolePermissionResponse> toResponses(List<RolePermission> rolePermissions);
}
