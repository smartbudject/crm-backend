package ru.smartbudject.crmbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.smartbudject.crmbackend.model.entity.Menu;
import ru.smartbudject.crmbackend.model.entity.MenuItem;

import java.util.List;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    List<MenuItem> findAllByMenuId(Long menuId);
}
