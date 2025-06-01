package com.accounting_system.accounting_system_backend.domain.repositories;

import com.accounting_system.accounting_system_backend.domain.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByCompanyIdAndName(UUID companyId, String name);

    List<Role> findAllByCompanyId(UUID companyId);
}
