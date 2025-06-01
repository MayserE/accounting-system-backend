package com.accounting_system.accounting_system_backend.services;

import com.accounting_system.accounting_system_backend.dto.requests.RegisterVendorRequest;
import com.accounting_system.accounting_system_backend.dto.responses.VendorResponse;
import com.accounting_system.accounting_system_backend.exceptions.vendors.VendorAlreadyExistsException;

import java.util.List;

public interface VendorService {
    List<VendorResponse> getVendors();

    VendorResponse registerVendor(RegisterVendorRequest request) throws VendorAlreadyExistsException;
}
