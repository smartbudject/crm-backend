package ru.smartbudject.crmbackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.smartbudject.crmbackend.model.entity.Account;


@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {


}
