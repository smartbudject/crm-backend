package ru.smartbudjet.crm_for_catering.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DiscountRepository extends JpaRepository<DiscountRepository, Long> {

}
