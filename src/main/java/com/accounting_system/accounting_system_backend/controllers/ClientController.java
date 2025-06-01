package com.accounting_system.accounting_system_backend.controllers;

import com.accounting_system.accounting_system_backend.config.annotations.AllowedPermissions;
import com.accounting_system.accounting_system_backend.domain.entities.enums.PermissionCode;
import com.accounting_system.accounting_system_backend.dto.requests.RegisterClientRequest;
import com.accounting_system.accounting_system_backend.dto.responses.ClientResponse;
import com.accounting_system.accounting_system_backend.exceptions.clients.ClientAlreadyExistsException;
import com.accounting_system.accounting_system_backend.services.ClientService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/clients")
public class ClientController {
    private final ClientService clientService;

    @AllowedPermissions(permissionCodes = PermissionCode.CLIENT_VISUALIZATION)
    @GetMapping
    public List<ClientResponse> getClients() {
        return clientService.getClients();
    }

    @AllowedPermissions(permissionCodes = PermissionCode.CLIENT_REGISTRATION)
    @PostMapping
    public ClientResponse registerClient(@Valid @RequestBody RegisterClientRequest request)
            throws ClientAlreadyExistsException {
        return clientService.registerClient(request);
    }
}
