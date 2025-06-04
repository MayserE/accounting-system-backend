package com.accounting_system.accounting_system_backend.domain.repositories;

import com.accounting_system.accounting_system_backend.domain.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
    List<Account> findAllByCompanyIdAndParentAccountIdIsNull(UUID companyId);

    List<Account> findAllByParentAccountId(UUID parentAccountId);

    Optional<Account> findByCompanyIdAndName(UUID companyId, String name);
}
