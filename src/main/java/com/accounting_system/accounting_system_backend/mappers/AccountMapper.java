package com.accounting_system.accounting_system_backend.mappers;

import com.accounting_system.accounting_system_backend.domain.entities.Account;
import com.accounting_system.accounting_system_backend.dto.requests.RegisterAccountRequest;
import com.accounting_system.accounting_system_backend.dto.responses.AccountResponse;
import org.mapstruct.Mapper;

@Mapper
public interface AccountMapper {
    AccountResponse toResponse(Account account);

    Account toEntity(RegisterAccountRequest request);
}
