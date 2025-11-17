package com.example.client.dto.controller;

import com.example.client.dto.request.EditClientRequest;
import com.example.client.dto.request.RegistrationRequest;
import com.example.client.dto.response.ClientResponse;
import com.example.client.entity.ClientEntity;
import com.example.client.exceptions.BadRequestException;
import com.example.client.exceptions.ClientAlreadyExistFoundException;
import com.example.client.exceptions.ClientNotFoundException;
import com.example.client.repository.ClientRepository;
import com.example.client.router.ClientRouters;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ClientApiController {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping(ClientRouters.REGISTRATION)
    public ClientResponse registration(@RequestBody RegistrationRequest request)
            throws BadRequestException, ClientAlreadyExistFoundException {

        request.validate();
        Optional<ClientEntity> check = clientRepository.findByEmail(request.getEmail());
        if (check.isPresent()) throw new ClientAlreadyExistFoundException();

        ClientEntity client = ClientEntity.builder()
                .lastName(request.getLastName())
                .firstName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        client = clientRepository.save(client);
        return ClientResponse.of(client);
    }

    @PostMapping(ClientRouters.ME)
    public ClientResponse edit(Principal principal, @RequestBody EditClientRequest request) {
        ClientEntity client = clientRepository
                .findByEmail(principal.getName())
                .orElseThrow(ClientAlreadyExistFoundException::new);

        client.setFirstName(request.getFirstName());
        client.setLastName(request.getLastName());
        client.setSecondName(request.getSecondName());
        client.setSnils(request.getSnils());
        client.setPassportId(request.getPassportId());
        client.setChronicDiseases(request.getChronicDiseases());
        client = clientRepository.save(client);
        return ClientResponse.of(client);
    }

    @GetMapping(ClientRouters.ME)
    public ClientResponse me(Principal principal) {
        return clientRepository
                .findByEmail(principal.getName())
                .map(ClientResponse::of)
                .orElseThrow(ClientNotFoundException::new);
    }
}
