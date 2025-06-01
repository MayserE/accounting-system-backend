package com.accounting_system.accounting_system_backend.services;

import com.accounting_system.accounting_system_backend.dto.requests.RegisterClientRequest;
import com.accounting_system.accounting_system_backend.dto.responses.ClientResponse;
import com.accounting_system.accounting_system_backend.exceptions.clients.ClientAlreadyExistsException;

import java.util.List;

public interface ClientService {
    List<ClientResponse> getClients();

    ClientResponse registerClient(RegisterClientRequest request) throws ClientAlreadyExistsException;
}
