package ru.smartbudjet.crm_for_catering.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.smartbudjet.crm_for_catering.exceptions.ForbiddenException;
import ru.smartbudjet.crm_for_catering.mapper.AccountMapper;
import ru.smartbudjet.crm_for_catering.model.dto.account.AccountRegisterRequest;
import ru.smartbudjet.crm_for_catering.model.entity.Account;
import ru.smartbudjet.crm_for_catering.repository.AccountRepository;
import ru.smartbudjet.crm_for_catering.repository.RoleRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    @Autowired
    private final AccountMapper accountMapper;


    @Transactional
    public void register(final AccountRegisterRequest accountRegisterRequest) {
        final Account account = accountMapper.mapToEntityRegister(accountRegisterRequest);

        if (accountRepository.findByEmail(account.getEmail())
                .isPresent()) {
            throw new ForbiddenException("Email уже используется");
        }

        account.setRole(roleRepository.findById(1L)
                .orElseThrow(() -> new ForbiddenException("Нет такой роли")));

        account.setCreatedAt(LocalDate.now());

        accountRepository.save(account);
    }

}
