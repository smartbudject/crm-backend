package ru.smartbudjet.crm_for_catering.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.smartbudjet.crm_for_catering.model.entity.PointSales;


@Repository
public interface PointSalesRepository extends JpaRepository<PointSales, Long> {

}
