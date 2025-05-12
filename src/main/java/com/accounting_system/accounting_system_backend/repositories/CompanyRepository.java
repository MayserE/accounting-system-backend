package com.accounting_system.accounting_system_backend.repositories;

import com.accounting_system.accounting_system_backend.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CompanyRepository extends JpaRepository<Company, UUID> {
}
