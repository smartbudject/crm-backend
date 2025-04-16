package ru.smartbudject.crmbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.smartbudject.crmbackend.model.entity.PointSales;


@Repository
public interface PointSalesRepository extends JpaRepository<PointSales, Long> {

}
