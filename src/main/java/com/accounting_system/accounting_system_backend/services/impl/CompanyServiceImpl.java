package com.accounting_system.accounting_system_backend.services.impl;

import com.accounting_system.accounting_system_backend.domain.entities.Company;
import com.accounting_system.accounting_system_backend.domain.entities.enums.CompanyStatus;
import com.accounting_system.accounting_system_backend.domain.repositories.CompanyRepository;
import com.accounting_system.accounting_system_backend.dto.requests.RegisterCompanyRequest;
import com.accounting_system.accounting_system_backend.dto.responses.CompanyResponse;
import com.accounting_system.accounting_system_backend.exceptions.companies.CompanyAlreadyExistsException;
import com.accounting_system.accounting_system_backend.mappers.CompanyMapper;
import com.accounting_system.accounting_system_backend.services.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    @Override
    public List<CompanyResponse> getCompanies() {
        List<Company> companies = companyRepository.findAll();
        return companyMapper.toResponses(companies);
    }

    @Override
    public CompanyResponse registerCompany(RegisterCompanyRequest request)
            throws CompanyAlreadyExistsException {
        String companyName = request.getName();
        Optional<Company> companyOptional = companyRepository.findByName(companyName.toUpperCase());
        if (companyOptional.isPresent()) {
            throw new CompanyAlreadyExistsException(
                    String.format("Ya existe una empresa con el nombre: %s.", companyName));
        }
        String taxId = request.getTaxId();
        companyOptional = companyRepository.findByTaxId(taxId.toUpperCase());
        if (companyOptional.isPresent()) {
            throw new CompanyAlreadyExistsException(
                    String.format("Ya existe una empresa con la identificación fiscal: %s.", taxId));
        }
        Company newCompany = companyMapper.toEntity(request);
        newCompany.setStatus(CompanyStatus.ACTIVE);
        newCompany.setName(newCompany.getName().toUpperCase());
        Company savedCompany = companyRepository.saveAndFlush(newCompany);
        return companyMapper.toResponse(savedCompany);
    }
}
