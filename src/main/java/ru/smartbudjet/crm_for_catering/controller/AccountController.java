package ru.smartbudjet.crm_for_catering.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.smartbudjet.crm_for_catering.model.dto.account.AccountRegisterRequest;
import ru.smartbudjet.crm_for_catering.service.AccountService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/register")
    public ResponseEntity registerAccount(
            @RequestBody AccountRegisterRequest accountRegisterRequest
    ) {
        accountService.register(accountRegisterRequest);
        return ResponseEntity.ok().build();
    }
}
