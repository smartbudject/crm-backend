package ru.smartbudject.crmbackend.model.dto;

import lombok.Data;


@Data
public class RegistrationRequestDTO {

    private String email;
    private String username;
    private String password;
}
