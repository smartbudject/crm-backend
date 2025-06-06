package ru.smartbudject.crmbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.smartbudject.crmbackend.model.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
