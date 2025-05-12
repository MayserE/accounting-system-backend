package com.accounting_system.accounting_system_backend.mappers;

import com.accounting_system.accounting_system_backend.dto.responses.CompanyResponse;
import com.accounting_system.accounting_system_backend.entities.Company;
import org.mapstruct.Mapper;

@Mapper
public interface CompanyMapper {
    CompanyResponse toResponse(Company company);
}
