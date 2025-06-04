package com.accounting_system.accounting_system_backend.services.impl;

import com.accounting_system.accounting_system_backend.config.SecurityContextHolder;
import com.accounting_system.accounting_system_backend.domain.entities.Account;
import com.accounting_system.accounting_system_backend.domain.entities.enums.AccountType;
import com.accounting_system.accounting_system_backend.domain.repositories.AccountRepository;
import com.accounting_system.accounting_system_backend.dto.requests.RegisterAccountRequest;
import com.accounting_system.accounting_system_backend.dto.responses.AccountResponse;
import com.accounting_system.accounting_system_backend.dto.responses.AccountWithSubAccountsResponse;
import com.accounting_system.accounting_system_backend.dto.responses.AuthenticatedUserResponse;
import com.accounting_system.accounting_system_backend.exceptions.BadRequestException;
import com.accounting_system.accounting_system_backend.exceptions.accounts.AccountAlreadyExistsException;
import com.accounting_system.accounting_system_backend.exceptions.accounts.AccountNotFoundException;
import com.accounting_system.accounting_system_backend.mappers.AccountMapper;
import com.accounting_system.accounting_system_backend.services.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Override
    public List<AccountWithSubAccountsResponse> getAccounts() {
        UUID companyId = getCompanyIdFromAuthenticatedUser();
        List<Account> rootAccounts = accountRepository.findAllByCompanyIdAndParentAccountIdIsNull(companyId);
        return getAccountsWithSubAccounts(rootAccounts);
    }

    @Override
    public AccountResponse registerAccount(RegisterAccountRequest request)
            throws BadRequestException, AccountAlreadyExistsException, AccountNotFoundException {
        UUID parentAccountId = request.getParentAccountId();
        AccountType accountType = request.getType();
        UUID companyId = getCompanyIdFromAuthenticatedUser();
        String name = request.getName();
        if (parentAccountId == null) {
            if (accountType == null) {
                throw new BadRequestException("El tipo de cuenta es requerido.");
            }
            verifyIfAccountExists(companyId, name);
            Account newAccount = accountMapper.toEntity(request);
            newAccount.setCompanyId(companyId);
            newAccount.setName(newAccount.getName().toUpperCase());
            Account savecAccount = accountRepository.saveAndFlush(newAccount);
            return accountMapper.toResponse(savecAccount);
        }
        if (accountType != null) {
            throw new BadRequestException("El tipo de cuenta no es requerido cuando se envía una cuenta padre.");
        }
        Account parentAccount = accountRepository.findById(parentAccountId).orElseThrow(
                () -> new AccountNotFoundException(
                        String.format("No existe la cuenta padre con el id: %s.", parentAccountId)
                )
        );
        verifyIfAccountExists(companyId, name);
        Account newAccount = accountMapper.toEntity(request);
        newAccount.setCompanyId(companyId);
        newAccount.setParentAccountId(parentAccount.getId());
        newAccount.setName(newAccount.getName().toUpperCase());
        newAccount.setType(parentAccount.getType());
        Account savecAccount = accountRepository.saveAndFlush(newAccount);
        return accountMapper.toResponse(savecAccount);
    }

    private UUID getCompanyIdFromAuthenticatedUser() {
        AuthenticatedUserResponse authenticatedUser = SecurityContextHolder.getAuthenticatedUser();
        return authenticatedUser.getCompanyUser().getCompanyId();
    }

    private List<AccountWithSubAccountsResponse> getAccountsWithSubAccounts(List<Account> accounts) {
        List<AccountWithSubAccountsResponse> accountsWithSubAccounts = new ArrayList<>();
        for (Account account : accounts) {
            AccountWithSubAccountsResponse accountWithSubAccounts = new AccountWithSubAccountsResponse();
            accountWithSubAccounts.setAccount(accountMapper.toResponse(account));
            List<Account> subAccounts = accountRepository.findAllByParentAccountId(account.getId());
            if (!subAccounts.isEmpty()) {
                accountWithSubAccounts.setSubAccounts(getAccountsWithSubAccounts(subAccounts));
            }
            accountsWithSubAccounts.add(accountWithSubAccounts);
        }
        return accountsWithSubAccounts;
    }

    private void verifyIfAccountExists(UUID companyId, String accountName) throws AccountAlreadyExistsException {
        Optional<Account> accountOptional =
                accountRepository.findByCompanyIdAndName(companyId, accountName.toUpperCase());
        if (accountOptional.isPresent()) {
            throw new AccountAlreadyExistsException(
                    String.format("Ya existe una cuenta con el nombre: %s.", accountName)
            );
        }
    }
}
