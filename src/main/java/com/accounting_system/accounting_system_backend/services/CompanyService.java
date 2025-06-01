package com.accounting_system.accounting_system_backend.services;

import com.accounting_system.accounting_system_backend.dto.requests.RegisterCompanyRequest;
import com.accounting_system.accounting_system_backend.dto.responses.CompanyResponse;
import com.accounting_system.accounting_system_backend.exceptions.companies.CompanyAlreadyExistsException;

import java.util.List;

public interface CompanyService {
    List<CompanyResponse> getCompanies();

    CompanyResponse registerCompany(RegisterCompanyRequest request) throws CompanyAlreadyExistsException;
}
