package ru.smartbudject.crmbackend.model.dto;

import lombok.Data;


@Data
public class SingInRequestDTO {

    private String email;
    private String password;

}
