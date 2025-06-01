package com.accounting_system.accounting_system_backend.mappers;

import com.accounting_system.accounting_system_backend.domain.entities.Client;
import com.accounting_system.accounting_system_backend.dto.requests.RegisterClientRequest;
import com.accounting_system.accounting_system_backend.dto.responses.ClientResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ClientMapper {
    List<ClientResponse> toResponses(List<Client> clients);

    Client toEntity(RegisterClientRequest request);

    ClientResponse toResponse(Client client);
}
