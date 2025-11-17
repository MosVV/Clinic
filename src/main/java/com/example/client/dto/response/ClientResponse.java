package com.example.client.dto.response;

import com.example.client.entity.ClientEntity;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ClientResponse {
    private Long id;

    protected String firstName;
    protected String lastName;
    protected String secondName;


    protected String snils;
    protected String passportId;
    protected String chronicDiseases;

    protected String email;

    public static ClientResponse of(ClientEntity item) {
        return ClientResponse.builder()
                .id(item.getId())
                .firstName(item.getFirstName())
                .lastName(item.getLastName())
                .secondName(item.getSecondName())
                .snils(item.getSnils())
                .passportId(item.getPassportId())
                .chronicDiseases(item.getChronicDiseases())
                .email(item.getEmail())
                .build();
    }

}
