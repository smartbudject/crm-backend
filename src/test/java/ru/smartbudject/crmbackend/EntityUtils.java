package ru.smartbudject.crmbackend;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ru.smartbudject.crmbackend.model.entity.Account;
import ru.smartbudject.crmbackend.model.entity.Role;
import ru.smartbudject.crmbackend.repository.AccountRepository;


@Component
public class EntityUtils {

    @Autowired
    private AccountRepository accountRepository;


    public Optional<Account> getUser(final long IdRole) {
        Account account = new Account();
        account.setUsername("admin");
        account.setPassword("admin");
        account.setRole(Role.builder()
                .id(IdRole)
                .build());
        return Optional.of(accountRepository.save(account));
    }

}
