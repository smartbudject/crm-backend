package ru.smartbudjet.crm_for_catering.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.smartbudjet.crm_for_catering.model.entity.OrderStatus;


@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {

}
