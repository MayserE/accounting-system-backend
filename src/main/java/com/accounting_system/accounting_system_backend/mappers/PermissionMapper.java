package com.accounting_system.accounting_system_backend.mappers;

import com.accounting_system.accounting_system_backend.domain.entities.Permission;
import com.accounting_system.accounting_system_backend.dto.responses.PermissionResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface PermissionMapper {
    List<PermissionResponse> toResponses(List<Permission> permissions);
}
