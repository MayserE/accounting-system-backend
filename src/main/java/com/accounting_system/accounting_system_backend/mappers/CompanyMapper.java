package com.accounting_system.accounting_system_backend.mappers;

import com.accounting_system.accounting_system_backend.domain.entities.Company;
import com.accounting_system.accounting_system_backend.dto.requests.RegisterCompanyRequest;
import com.accounting_system.accounting_system_backend.dto.responses.CompanyResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CompanyMapper {
    CompanyResponse toResponse(Company company);

    Company toEntity(RegisterCompanyRequest request);

    List<CompanyResponse> toResponses(List<Company> companies);
}
