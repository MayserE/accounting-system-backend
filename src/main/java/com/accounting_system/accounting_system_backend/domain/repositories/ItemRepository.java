package com.accounting_system.accounting_system_backend.domain.repositories;

import com.accounting_system.accounting_system_backend.domain.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ItemRepository extends JpaRepository<Item, UUID> {
    List<Item> findAllByCompanyId(UUID companyId);

    Optional<Item> findByCompanyIdAndName(UUID companyId, String name);
}
