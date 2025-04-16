package ru.smartbudject.crmbackend;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ru.smartbudject.crmbackend.model.entity.Account;
import ru.smartbudject.crmbackend.model.entity.PointSales;
import ru.smartbudject.crmbackend.model.entity.Role;
import ru.smartbudject.crmbackend.repository.AccountRepository;
import ru.smartbudject.crmbackend.repository.PointSalesRepository;


@Component
public class EntityUtils {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PointSalesRepository pointSalesRepository;


    @Transactional
    public Optional<Account> getUser(final long IdRole) {
        Account account = new Account();
        account.setUsername("admin");
        account.setPassword("admin");
        account.setRole(Role.builder()
                .id(IdRole)
                .build());
        return Optional.of(accountRepository.save(account));
    }


    @Transactional
    public PointSales getPointSales(final Account account) {
        PointSales pointSales = new PointSales();
        pointSales.setName("Point 1");
        pointSales.setAddress("Moscow");
        pointSales.setAccount(account);
        pointSales.setVersion(1);
        return pointSalesRepository.save(pointSales);
    }

}
