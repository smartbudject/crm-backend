package ru.smartbudject.crmbackend.model.dto.account;

import lombok.Data;


@Data
public class SingInRequestDTO {

    private String email;
    private String password;

}
