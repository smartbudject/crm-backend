package ru.smartbudject.crmbackend.service;

import java.util.Optional;

import ru.smartbudject.crmbackend.model.dto.account.RegistrationRequestDTO;
import ru.smartbudject.crmbackend.model.entity.Account;


/**
 * Сервис для работы с аккаунтами.
 */
public interface AccountService {

    /**
     * Метод для регистрации пользователя.
     * @param registrationRequestDTO
     */
    void registration(RegistrationRequestDTO registrationRequestDTO);

    /**
     * Метод для получения пользователя из контекста.
     * @return Optiona<Account>
     */
    Optional<Account> tryGetAuthenticated();
}
