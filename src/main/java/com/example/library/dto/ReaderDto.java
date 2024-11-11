package com.example.library.dto;

import java.time.LocalDate;

public class ReaderDto {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate accountCreated;

    // Konstruktor bezparametrowy
    public ReaderDto() {
    }

    // Konstruktor z parametrami
    public ReaderDto(Long id, String firstName, String lastName, LocalDate accountCreated) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountCreated = accountCreated;
    }

    // Gettery i settery
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getAccountCreated() {
        return accountCreated;
    }

    public void setAccountCreated(LocalDate accountCreated) {
        this.accountCreated = accountCreated;
    }
}
