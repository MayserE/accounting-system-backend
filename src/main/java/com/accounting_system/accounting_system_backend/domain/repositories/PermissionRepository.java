package com.accounting_system.accounting_system_backend.domain.repositories;

import com.accounting_system.accounting_system_backend.domain.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, UUID> {
}
