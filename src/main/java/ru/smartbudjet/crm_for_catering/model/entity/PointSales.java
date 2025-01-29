package ru.smartbudjet.crm_for_catering.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "point_sales")
public class PointSales  extends AbstractEntity {

    @ManyToOne
    private Account account;

    private String name;

    private String address;
}
