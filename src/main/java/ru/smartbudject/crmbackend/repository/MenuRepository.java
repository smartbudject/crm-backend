package ru.smartbudject.crmbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.smartbudject.crmbackend.model.entity.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}
