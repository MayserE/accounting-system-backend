package com.accounting_system.accounting_system_backend.services.impl;

import com.accounting_system.accounting_system_backend.domain.entities.Permission;
import com.accounting_system.accounting_system_backend.domain.repositories.PermissionRepository;
import com.accounting_system.accounting_system_backend.dto.responses.PermissionResponse;
import com.accounting_system.accounting_system_backend.mappers.PermissionMapper;
import com.accounting_system.accounting_system_backend.services.PermissionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PermissionServiceImpl implements PermissionService {
    private final PermissionRepository permissionRepository;
    private final PermissionMapper permissionMapper;

    @Override
    public List<PermissionResponse> getPermissions() {
        List<Permission> permissions = permissionRepository.findAll();
        return permissionMapper.toResponses(permissions);
    }
}
