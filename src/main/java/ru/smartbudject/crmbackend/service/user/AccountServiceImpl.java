package ru.smartbudject.crmbackend.service.user;

import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.smartbudject.crmbackend.mapper.account.AccountMapper;
import ru.smartbudject.crmbackend.model.dto.account.RegistrationRequestDTO;
import ru.smartbudject.crmbackend.model.entity.Account;
import ru.smartbudject.crmbackend.model.entity.Role;
import ru.smartbudject.crmbackend.repository.AccountRepository;
import ru.smartbudject.crmbackend.security.UserDetailsImpl;
import ru.smartbudject.crmbackend.service.AccountService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;


/**
 * Реализация сервиса для взаимодействием аккаунта.
 */
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccountMapper accountMapper;
    @PersistenceContext
    private EntityManager entityManager;



    /**
     * Метод для регистрации пользователя.
     * @param registrationRequestDTO
     */
    @Override
    @Transactional
    public void registration(final RegistrationRequestDTO registrationRequestDTO) {
        if (registrationRequestDTO.getEmail() != null && accountRepository.findByEmail(registrationRequestDTO.getEmail())
                .isPresent()) {
            return;
        }

        final Account account = accountMapper.mapReqistration(registrationRequestDTO);
        account.setPassword(passwordEncoder.encode(registrationRequestDTO.getPassword()));
        account.setRole(Role.builder()
                .id(2L)
                .build());
        account.setIsDelete(Boolean.FALSE);
        accountRepository.save(account);
    }


    /**
     * Метод для получения пользователя из контекста.
     * @return Optiona<Account>
     */
    @Override
    public Optional<Account> tryGetAuthenticated() {
        final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal == null || principal.equals("anonymousUser")) {
            return Optional.empty();
        }
        final UserDetailsImpl userDetails = (UserDetailsImpl) principal;
        final Account detachedAccount = userDetails.user();
        return Optional.ofNullable(entityManager.find(Account.class, detachedAccount.getId()));
    }

}
