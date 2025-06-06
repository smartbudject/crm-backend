package ru.smartbudject.crmbackend.security;

import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import ru.smartbudject.crmbackend.model.entity.Account;
import ru.smartbudject.crmbackend.repository.AccountRepository;


/**
 * Реализация UserDetailsService.
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AccountRepository accountRepository;


    /**
     * Поиск пользователя.
     * @param email
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {

        final Optional<Account> accountOpt = accountRepository.findAll()
                .stream()
                .filter(account -> account.getEmail()
                        .equals(email))
                .findFirst();

        if (accountOpt.isEmpty()) {
            throw new UsernameNotFoundException("No find account");
        } else {
            return new UserDetailsImpl(accountOpt.get());
        }
    }

}
