package com.example.client.dto.request;

import lombok.Getter;

@Getter
public class EditClientRequest {
    private String firstName;
    private String lastName;
    private String secondName;
    private String snils;
    private String passportId;
    private String chronicDiseases;
}
