package com.accounting_system.accounting_system_backend.domain.repositories;

import com.accounting_system.accounting_system_backend.domain.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CompanyRepository extends JpaRepository<Company, UUID> {
    Optional<Company> findByName(String name);

    Optional<Company> findByTaxId(String taxId);
}
