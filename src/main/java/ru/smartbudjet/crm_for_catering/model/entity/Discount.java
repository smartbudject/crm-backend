package ru.smartbudjet.crm_for_catering.model.entity;

import jakarta.persistence.Column;
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
@Table(name = "discount")
public class Discount extends AbstractEntity {

    @Column(name = "discount_name")
    private String discountName;

    @Column(name = "discount_type")
    private String discountType;

    @Column(name = "discount_session")
    private String discountSession;

    private Double discount;

    @Column(name = "min_price_order")
    private Double minPriceOrder;

    @ManyToOne
    private PointSales pointSales;

}
