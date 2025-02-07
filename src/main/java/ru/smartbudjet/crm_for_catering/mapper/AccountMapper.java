package ru.smartbudjet.crm_for_catering.mapper;


import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import ru.smartbudjet.crm_for_catering.model.dto.account.AccountRegisterRequest;
import ru.smartbudjet.crm_for_catering.model.entity.Account;

import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class AccountMapper {

    private final ModelMapper modelMapper;

    public Account mapToEntityRegister(AccountRegisterRequest accountRegisterRequest) {
        return modelMapper.map(accountRegisterRequest, Account.class);
    }

}
