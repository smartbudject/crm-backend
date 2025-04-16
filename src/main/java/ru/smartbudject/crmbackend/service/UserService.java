package ru.smartbudject.crmbackend.service;

import java.util.Optional;

import ru.smartbudject.crmbackend.model.dto.RegistrationRequestDTO;
import ru.smartbudject.crmbackend.model.entity.Account;



public interface UserService {

    void registration(RegistrationRequestDTO registrationRequestDTO);

    Optional<Account> tryGetAuthenticated();
}
