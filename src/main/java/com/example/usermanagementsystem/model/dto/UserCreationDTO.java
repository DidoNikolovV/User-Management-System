package com.example.usermanagementsystem.model.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserCreationDTO {
    @NotNull
    @Pattern(regexp = "^[a-zA-Z\\-]+$", message = "Invalid name format")
    @Size(min = 2, max = 10)
    private String firstName;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z\\-]+$", message = "Invalid name format")
    @Size(min = 2, max = 10)
    private String lastName;

    @NotNull
    private String dateOfBirth;

    @Column(unique = true, nullable = false)
    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid phone number")
    private String phoneNumber;

    @Email
    private String email;

    public UserCreationDTO(String firstName, String lastName, String dateOfBirth, String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
