package ru.smartbudject.crmbackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.smartbudject.crmbackend.model.dto.SingInRequestDTO;
import ru.smartbudject.crmbackend.model.dto.RegistrationRequestDTO;
import ru.smartbudject.crmbackend.model.entity.Account;
import ru.smartbudject.crmbackend.config.security.JwtService;
import ru.smartbudject.crmbackend.config.security.UserDetailsImpl;
import ru.smartbudject.crmbackend.service.user.UserService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class RegistrationController {


    private final UserService registrationService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    @PostMapping("registration")
    public ResponseEntity<Void> registration(@RequestBody RegistrationRequestDTO registrationRequestDTO) {
        registrationService.registration(registrationRequestDTO);
        return ResponseEntity.ok()
                .build();
    }


    @PostMapping("/sing-in")
    public ResponseEntity<?> singIn(@RequestBody SingInRequestDTO loginRequestDTO) {
        Authentication authentication ;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(), loginRequestDTO.getPassword()));

        } catch (BadCredentialsException e) {
            return ResponseEntity.badRequest()
                    .build();
        }
        final Account account = ((UserDetailsImpl) authentication.getPrincipal()).getUser();

        if (Boolean.TRUE.equals(account.getIsDelete())) {
            return ResponseEntity.badRequest()
                    .build();
        }

        return ResponseEntity.ok(jwtService.generateToken(account.getEmail(), account.getRole()
                .getName(), account.getUsername()));
    }

}
