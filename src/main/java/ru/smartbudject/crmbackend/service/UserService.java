package ru.smartbudject.crmbackend.service;

import org.springframework.stereotype.Service;

import ru.smartbudject.crmbackend.model.dto.RegistrationRequestDTO;

@Service
public interface UserService {

    void registration(RegistrationRequestDTO registrationRequestDTO);
}
