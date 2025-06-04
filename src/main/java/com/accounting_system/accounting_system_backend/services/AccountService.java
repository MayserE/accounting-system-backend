package com.accounting_system.accounting_system_backend.services;

import com.accounting_system.accounting_system_backend.dto.requests.RegisterAccountRequest;
import com.accounting_system.accounting_system_backend.dto.responses.AccountResponse;
import com.accounting_system.accounting_system_backend.dto.responses.AccountWithSubAccountsResponse;
import com.accounting_system.accounting_system_backend.exceptions.BadRequestException;
import com.accounting_system.accounting_system_backend.exceptions.accounts.AccountAlreadyExistsException;
import com.accounting_system.accounting_system_backend.exceptions.accounts.AccountNotFoundException;

import java.util.List;

public interface AccountService {
    List<AccountWithSubAccountsResponse> getAccounts();

    AccountResponse registerAccount(RegisterAccountRequest request)
            throws BadRequestException, AccountAlreadyExistsException, AccountNotFoundException;
}
