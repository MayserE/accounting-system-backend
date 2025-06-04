package com.accounting_system.accounting_system_backend.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccountWithSubAccountsResponse {
    private AccountResponse account;
    private List<AccountWithSubAccountsResponse> subAccounts;
}
