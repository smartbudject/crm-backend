package ru.smartbudject.crmbackend.mapper.account;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import ru.smartbudject.crmbackend.model.dto.account.RegistrationRequestDTO;
import ru.smartbudject.crmbackend.model.entity.Account;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AccountMapper {

    Account mapReqistration(final RegistrationRequestDTO registrationRequestDTO);
}
