package ru.smartbudject.crmbackend.service.user;

import java.util.Optional;

import ru.smartbudject.crmbackend.model.dto.RegistrationRequestDTO;
import ru.smartbudject.crmbackend.model.entity.Account;



public interface UserService {

    boolean registration(RegistrationRequestDTO registrationRequestDTO);

    Optional<Account> tryGetAuthenticated();
}
