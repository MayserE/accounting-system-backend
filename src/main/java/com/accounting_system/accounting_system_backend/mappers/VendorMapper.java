package com.accounting_system.accounting_system_backend.mappers;

import com.accounting_system.accounting_system_backend.domain.entities.Vendor;
import com.accounting_system.accounting_system_backend.dto.requests.RegisterVendorRequest;
import com.accounting_system.accounting_system_backend.dto.responses.VendorResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface VendorMapper {
    List<VendorResponse> toResponses(List<Vendor> vendors);

    Vendor toEntity(RegisterVendorRequest request);

    VendorResponse toResponse(Vendor vendor);
}
