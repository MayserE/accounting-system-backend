package com.accounting_system.accounting_system_backend.mappers;

import com.accounting_system.accounting_system_backend.dto.responses.CompanyUserResponse;
import com.accounting_system.accounting_system_backend.entities.CompanyUser;
import org.mapstruct.Mapper;

@Mapper
public interface CompanyUserMapper {
    CompanyUserResponse toResponse(CompanyUser companyUser);
}
