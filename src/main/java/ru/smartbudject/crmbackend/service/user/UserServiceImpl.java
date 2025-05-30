package ru.smartbudject.crmbackend.service.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.smartbudject.crmbackend.config.security.UserDetailsImpl;
import ru.smartbudject.crmbackend.mapper.AccountMapper;
import ru.smartbudject.crmbackend.model.dto.RegistrationRequestDTO;
import ru.smartbudject.crmbackend.model.entity.Account;
import ru.smartbudject.crmbackend.model.entity.Role;
import ru.smartbudject.crmbackend.repository.AccountRepository;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccountMapper accountMapper;
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    @Transactional
    public boolean registration(final RegistrationRequestDTO registrationRequestDTO) {
        if (
                (
                        registrationRequestDTO.getEmail() != null
                        && accountRepository.findByEmail(registrationRequestDTO.getEmail()).isPresent()
                )
                ||
                (
                        registrationRequestDTO.getUsername() != null &&
                        accountRepository.findByUsername(registrationRequestDTO.getUsername()).isPresent()
                )
        ) {
            return false;
        }

        Account account = accountMapper.mapReqistration(registrationRequestDTO);
        account.setPassword(passwordEncoder.encode(registrationRequestDTO.getPassword()));
        account.setRole(Role.builder()
                .id(1L) //1 - user, 2 - ADMIN
                .build());
        account.setIsDelete(Boolean.FALSE);
        accountRepository.save(account);
        return true;
    }


    @Override
    public Optional<Account> tryGetAuthenticated() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal == null || principal.equals("anonymousUser")) return Optional.empty();
        UserDetailsImpl userDetails = (UserDetailsImpl) principal;
        Account detachedAccount = userDetails.getUser();
        return Optional.ofNullable(entityManager.find(Account.class, detachedAccount.getId()));
    }


}
