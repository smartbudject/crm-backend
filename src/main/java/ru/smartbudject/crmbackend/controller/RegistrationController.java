package ru.smartbudject.crmbackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.smartbudject.crmbackend.model.dto.SingInRequestDTO;
import ru.smartbudject.crmbackend.model.dto.RegistrationRequestDTO;
import ru.smartbudject.crmbackend.model.entity.Account;
import ru.smartbudject.crmbackend.security.JwtService;
import ru.smartbudject.crmbackend.security.UserDetailsImpl;
import ru.smartbudject.crmbackend.service.UserService;
import ru.smartbudject.crmbackend.service.user.UserServiceImpl;

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
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(), loginRequestDTO.getPassword()));

        final Account account = ((UserDetailsImpl) authentication.getPrincipal()).getUser();

        if (Boolean.TRUE.equals(account.getIsDelete())) {
            return ResponseEntity.badRequest()
                    .build();
        }

        return ResponseEntity.ok(jwtService.generateToken(account.getEmail(), account.getRole()
                .getName(), account.getUsername()));
    }


    @GetMapping("/getRole")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<String> getRole() {
        return ResponseEntity.ok("ROLE_ADMIN");
    }

}
