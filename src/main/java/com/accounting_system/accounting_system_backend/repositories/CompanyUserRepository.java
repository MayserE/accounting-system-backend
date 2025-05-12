package com.accounting_system.accounting_system_backend.repositories;

import com.accounting_system.accounting_system_backend.entities.CompanyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CompanyUserRepository extends JpaRepository<CompanyUser, UUID> {
    Optional<CompanyUser> findFirstByUserIdOrderByCreatedAtDesc(UUID userId);
}
