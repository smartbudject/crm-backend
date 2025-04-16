package ru.smartbudject.crmbackend.model.dto.product;

import ru.smartbudject.crmbackend.model.dto.pointsales.PointSalesDTO;

import lombok.Data;


@Data
public class AddProductRequest {

    private String nameProduct;
    private Integer count;
    private Double price;
    private PointSalesDTO pointSales;
}
