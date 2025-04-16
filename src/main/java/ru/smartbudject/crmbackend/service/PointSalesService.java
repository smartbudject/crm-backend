package ru.smartbudject.crmbackend.service;

import ru.smartbudject.crmbackend.model.dto.pointsales.AddPointSalesRequest;


public interface PointSalesService {

    Long addPointSales(AddPointSalesRequest addPointSalesRequest);

    Long updatePointSales(AddPointSalesRequest addPointSalesRequest, Long id);

}
