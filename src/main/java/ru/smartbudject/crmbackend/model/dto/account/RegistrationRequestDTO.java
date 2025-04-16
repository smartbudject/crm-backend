package ru.smartbudject.crmbackend.model.dto.account;

import lombok.Data;


@Data
public class RegistrationRequestDTO {

    private String email;
    private String username;
    private String password;

}
