package ru.smartbudject.crmbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.smartbudject.crmbackend.model.entity.order.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByClientId(Long userId);
}
