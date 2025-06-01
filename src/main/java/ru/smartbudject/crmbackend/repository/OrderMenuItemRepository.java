package ru.smartbudject.crmbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.smartbudject.crmbackend.model.entity.order.OrderMenuItem;

public interface OrderMenuItemRepository extends JpaRepository<OrderMenuItem, Long>{
}
