package com.accounting_system.accounting_system_backend.services.impl;

import com.accounting_system.accounting_system_backend.config.SecurityContextHolder;
import com.accounting_system.accounting_system_backend.domain.entities.Vendor;
import com.accounting_system.accounting_system_backend.domain.repositories.VendorRepository;
import com.accounting_system.accounting_system_backend.dto.requests.RegisterVendorRequest;
import com.accounting_system.accounting_system_backend.dto.responses.AuthenticatedUserResponse;
import com.accounting_system.accounting_system_backend.dto.responses.VendorResponse;
import com.accounting_system.accounting_system_backend.exceptions.vendors.VendorAlreadyExistsException;
import com.accounting_system.accounting_system_backend.mappers.VendorMapper;
import com.accounting_system.accounting_system_backend.services.VendorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class VendorServiceImpl implements VendorService {
    private final VendorRepository vendorRepository;
    private final VendorMapper vendorMapper;

    @Override
    public List<VendorResponse> getVendors() {
        UUID companyId = getCompanyIdFromAuthenticatedUser();
        List<Vendor> vendors = vendorRepository.findAllByCompanyId(companyId);
        return vendorMapper.toResponses(vendors);
    }

    @Override
    public VendorResponse registerVendor(RegisterVendorRequest request) throws VendorAlreadyExistsException {
        UUID companyId = getCompanyIdFromAuthenticatedUser();
        String name = request.getName();
        Optional<Vendor> vendorOptional = vendorRepository.findByCompanyIdAndName(companyId, name.toUpperCase());
        if (vendorOptional.isPresent()) {
            throw new VendorAlreadyExistsException(String.format("Ya existe un proveedor con el nombre: %s.", name));
        }
        Vendor newVendor = vendorMapper.toEntity(request);
        newVendor.setCompanyId(companyId);
        newVendor.setName(newVendor.getName().toUpperCase());
        newVendor.setEmail(newVendor.getEmail().toLowerCase());
        Vendor savedVendor = vendorRepository.saveAndFlush(newVendor);
        return vendorMapper.toResponse(savedVendor);
    }

    private UUID getCompanyIdFromAuthenticatedUser() {
        AuthenticatedUserResponse authenticatedUser = SecurityContextHolder.getAuthenticatedUser();
        return authenticatedUser.getCompanyUser().getCompanyId();
    }
}
