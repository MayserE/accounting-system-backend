package com.accounting_system.accounting_system_backend.controllers;

import com.accounting_system.accounting_system_backend.config.annotations.SuperAdminRequired;
import com.accounting_system.accounting_system_backend.dto.requests.RegisterCompanyRequest;
import com.accounting_system.accounting_system_backend.dto.responses.CompanyResponse;
import com.accounting_system.accounting_system_backend.exceptions.companies.CompanyAlreadyExistsException;
import com.accounting_system.accounting_system_backend.services.CompanyService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/companies")
public class CompanyController {
    private final CompanyService companyService;

    @SuperAdminRequired
    @GetMapping
    public List<CompanyResponse> getCompanies() {
        return companyService.getCompanies();
    }

    @SuperAdminRequired
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyResponse registerCompany(@Valid @RequestBody RegisterCompanyRequest request)
            throws CompanyAlreadyExistsException {
        return companyService.registerCompany(request);
    }
}
