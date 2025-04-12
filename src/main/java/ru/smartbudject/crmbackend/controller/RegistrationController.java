package ru.smartbudject.crmbackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.smartbudject.crmbackend.model.dto.RegistrationRequestDTO;
import ru.smartbudject.crmbackend.service.RegistrationService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/registration")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;



    @PostMapping()
    public ResponseEntity<Void> registration(@RequestBody RegistrationRequestDTO registrationRequestDTO) {
        registrationService.registration(registrationRequestDTO);
        return ResponseEntity.ok().build();
    }
}
