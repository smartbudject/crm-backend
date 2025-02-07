package ru.smartbudjet.crm_for_catering.model.dto.account;

import lombok.Data;


@Data
public class AccountRegisterRequest {

    public String username;
    public String password;
    public String email;
}
