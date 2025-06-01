package com.accounting_system.accounting_system_backend.services.impl;

import com.accounting_system.accounting_system_backend.config.SecurityContextHolder;
import com.accounting_system.accounting_system_backend.domain.entities.Client;
import com.accounting_system.accounting_system_backend.domain.repositories.ClientRepository;
import com.accounting_system.accounting_system_backend.dto.requests.RegisterClientRequest;
import com.accounting_system.accounting_system_backend.dto.responses.AuthenticatedUserResponse;
import com.accounting_system.accounting_system_backend.dto.responses.ClientResponse;
import com.accounting_system.accounting_system_backend.exceptions.clients.ClientAlreadyExistsException;
import com.accounting_system.accounting_system_backend.mappers.ClientMapper;
import com.accounting_system.accounting_system_backend.services.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public List<ClientResponse> getClients() {
        UUID companyId = getCompanyIdFromAuthenticatedUser();
        List<Client> clients = clientRepository.findAllByCompanyId(companyId);
        return clientMapper.toResponses(clients);
    }

    @Override
    public ClientResponse registerClient(RegisterClientRequest request) throws ClientAlreadyExistsException {
        UUID companyId = getCompanyIdFromAuthenticatedUser();
        String documentNumber = request.getDocumentNumber();
        Optional<Client> clientOptional =
                clientRepository.findByCompanyIdAndDocumentNumber(companyId, documentNumber.toUpperCase());
        if (clientOptional.isPresent()) {
            throw new ClientAlreadyExistsException(
                    String.format("Ya existe un cliente con el número de documento: %s.", documentNumber)
            );
        }
        Client newClient = clientMapper.toEntity(request);
        newClient.setCompanyId(companyId);
        newClient.setName(newClient.getName().toUpperCase());
        newClient.setDocumentNumber(newClient.getDocumentNumber().toUpperCase());
        newClient.setEmail(newClient.getEmail().toLowerCase());
        String taxId = newClient.getTaxId();
        newClient.setTaxId(taxId == null ? null : taxId.toUpperCase());
        Client savedClient = clientRepository.saveAndFlush(newClient);
        return clientMapper.toResponse(savedClient);
    }

    private UUID getCompanyIdFromAuthenticatedUser() {
        AuthenticatedUserResponse authenticatedUser = SecurityContextHolder.getAuthenticatedUser();
        return authenticatedUser.getCompanyUser().getCompanyId();
    }
}
