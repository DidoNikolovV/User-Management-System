package com.example.usermanagementsystem.model.dto;

public class UserDetailsDTO {

    private String firstName;

    private String lastName;

    private String dateOfBirth;

    private String phoneNumber;

    private String email;

    public UserDetailsDTO(String firstName, String lastName, String dateOfBirth, String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }
}
