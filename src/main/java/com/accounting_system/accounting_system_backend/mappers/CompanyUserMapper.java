package com.accounting_system.accounting_system_backend.mappers;

import com.accounting_system.accounting_system_backend.domain.entities.CompanyUser;
import com.accounting_system.accounting_system_backend.dto.requests.RegisterCompanyUserRequest;
import com.accounting_system.accounting_system_backend.dto.responses.CompanyUserResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = {CompanyMapper.class, UserMapper.class, RoleMapper.class})
public interface CompanyUserMapper {
    CompanyUserResponse toResponse(CompanyUser companyUser);

    List<CompanyUserResponse> toResponses(List<CompanyUser> companyUsers);

    CompanyUser toEntity(RegisterCompanyUserRequest companyUser);
}
