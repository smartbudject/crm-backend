package ru.smartbudject.crmbackend.controller.account;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.smartbudject.crmbackend.model.dto.account.SingInRequestDTO;
import ru.smartbudject.crmbackend.model.dto.account.RegistrationRequestDTO;
import ru.smartbudject.crmbackend.model.entity.Account;
import ru.smartbudject.crmbackend.security.JwtService;
import ru.smartbudject.crmbackend.security.UserDetailsImpl;
import ru.smartbudject.crmbackend.service.AccountService;

import lombok.RequiredArgsConstructor;

/**
 * Контроллер для взаимодействия пользователя.
 */
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class RegistrationController {


    private final AccountService registrationService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    /**
     * Метод для регистрации.
     * @param registrationRequestDTO - dto для регистрации.
     * @return ResponseEntity.
     */
    @PostMapping("registration")
    public ResponseEntity<Void> registration(
            @RequestBody final RegistrationRequestDTO registrationRequestDTO
    ) {
        registrationService.registration(registrationRequestDTO);
        return ResponseEntity.ok()
                .build();
    }


    /**
     * Метод для получения jwt токена.
     * @param singInRequestDTO - dto
     * @return jwt
     */
    @PostMapping("/sing-in")
    public ResponseEntity<?> singIn(
            @RequestBody final SingInRequestDTO singInRequestDTO
    ) {
        final Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            singInRequestDTO.getEmail(),
                            singInRequestDTO.getPassword()
                    ));

        } catch (BadCredentialsException e) {
            return ResponseEntity.badRequest()
                    .build();
        }
        final Account account = ((UserDetailsImpl) authentication.getPrincipal()).user();

        if (Boolean.TRUE.equals(account.getIsDelete())) {
            return ResponseEntity.badRequest()
                    .build();
        }

        return ResponseEntity.ok(jwtService.generateToken(account.getEmail(), account.getRole()
                .getName(), account.getUsername()));
    }

}
