package com.accounting_system.accounting_system_backend.controllers;

import com.accounting_system.accounting_system_backend.config.annotations.AllowedPermissions;
import com.accounting_system.accounting_system_backend.domain.entities.enums.PermissionCode;
import com.accounting_system.accounting_system_backend.dto.requests.RegisterAccountRequest;
import com.accounting_system.accounting_system_backend.dto.responses.AccountResponse;
import com.accounting_system.accounting_system_backend.dto.responses.AccountWithSubAccountsResponse;
import com.accounting_system.accounting_system_backend.exceptions.BadRequestException;
import com.accounting_system.accounting_system_backend.exceptions.accounts.AccountAlreadyExistsException;
import com.accounting_system.accounting_system_backend.exceptions.accounts.AccountNotFoundException;
import com.accounting_system.accounting_system_backend.services.AccountService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/accounts")
public class AccountController {
    private final AccountService accountService;

    @AllowedPermissions(permissionCodes = PermissionCode.ACCOUNT_VISUALIZATION)
    @GetMapping
    public List<AccountWithSubAccountsResponse> getAccounts() {
        return accountService.getAccounts();
    }

    @AllowedPermissions(permissionCodes = PermissionCode.ACCOUNT_REGISTRATION)
    @PostMapping
    public AccountResponse registerAccount(@Valid @RequestBody RegisterAccountRequest request)
            throws AccountAlreadyExistsException, BadRequestException, AccountNotFoundException {
        return accountService.registerAccount(request);
    }
}
