package com.example.client.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@Entity
@NoArgsConstructor
@Table(name = "clients")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    protected String firstName;
    protected String lastName;
    protected String secondName;


    protected String snils;
    protected String passportId;
    protected String chronicDiseases;

    protected String email;
    protected String password;


}
