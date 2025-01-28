package ru.smartbudjet.crm_for_catering.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product extends AbstractEntity {
    @Column(name = "name_product")
    private String nameProduct;
    private Integer count;
    private Double price;
}
