package ru.smartbudjet.crm_for_catering.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.smartbudjet.crm_for_catering.model.entity.Discount;


@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {

}
