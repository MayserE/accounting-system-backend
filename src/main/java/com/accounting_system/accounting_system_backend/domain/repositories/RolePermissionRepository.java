package com.accounting_system.accounting_system_backend.domain.repositories;

import com.accounting_system.accounting_system_backend.domain.entities.RolePermission;
import com.accounting_system.accounting_system_backend.domain.entities.enums.PermissionCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermission, UUID> {
    Optional<RolePermission> findByRoleIdAndPermissionCode(UUID roleId, PermissionCode permissionCode);

    List<RolePermission> findAllByRoleId(UUID roleId);
}
