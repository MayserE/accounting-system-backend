package com.accounting_system.accounting_system_backend.controllers;

import com.accounting_system.accounting_system_backend.config.annotations.AllowedPermissions;
import com.accounting_system.accounting_system_backend.domain.entities.enums.PermissionCode;
import com.accounting_system.accounting_system_backend.dto.requests.RegisterVendorRequest;
import com.accounting_system.accounting_system_backend.dto.responses.VendorResponse;
import com.accounting_system.accounting_system_backend.exceptions.vendors.VendorAlreadyExistsException;
import com.accounting_system.accounting_system_backend.services.VendorService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/vendors")
public class VendorController {
    private final VendorService vendorService;

    @AllowedPermissions(permissionCodes = PermissionCode.VENDOR_VISUALIZATION)
    @GetMapping
    public List<VendorResponse> getVendors() {
        return vendorService.getVendors();
    }

    @AllowedPermissions(permissionCodes = PermissionCode.VENDOR_REGISTRATION)
    @PostMapping
    public VendorResponse registerVendor(@Valid @RequestBody RegisterVendorRequest request)
            throws VendorAlreadyExistsException {
        return vendorService.registerVendor(request);
    }
}
