package ru.smartbudject.crmbackend.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ru.smartbudject.crmbackend.model.entity.Account;
import ru.smartbudject.crmbackend.repository.AccountRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AccountRepository userRepository;


    public UserDetailsServiceImpl(AccountRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Account> accountOpt = userRepository.getAllAccounts()
                .stream()
                .filter(account -> account.getUsername()
                        .equals(username))
                .findFirst();

        if (accountOpt.isEmpty()) {
            throw new UsernameNotFoundException("No find username");
        } else {
            return new UserDetailsImpl(accountOpt.get());
        }
    }

}
