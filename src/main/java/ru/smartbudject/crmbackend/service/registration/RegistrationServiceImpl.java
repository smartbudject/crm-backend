package ru.smartbudject.crmbackend.service.registration;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.smartbudject.crmbackend.mapper.account.AccountMapper;
import ru.smartbudject.crmbackend.model.dto.RegistrationRequestDTO;
import ru.smartbudject.crmbackend.model.entity.Account;
import ru.smartbudject.crmbackend.model.entity.Role;
import ru.smartbudject.crmbackend.repository.AccountRepository;
import ru.smartbudject.crmbackend.service.RegistrationService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccountMapper accountMapper;


    @Override
    @Transactional
    public void registration(final RegistrationRequestDTO registrationRequestDTO) {
        if (registrationRequestDTO.getEmail() != null && accountRepository.findByEmail(registrationRequestDTO.getEmail())
                .isPresent()) {
            return;
        }

        Account account = accountMapper.mapReqistration(registrationRequestDTO);
        account.setPassword(passwordEncoder.encode(registrationRequestDTO.getPassword()));
        account.setRole(Role.builder()
                .id(2L)
                .build());
        account.setIsDelete(Boolean.FALSE);
        accountRepository.save(account);
    }

}
