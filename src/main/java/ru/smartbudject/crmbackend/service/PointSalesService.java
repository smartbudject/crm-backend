package ru.smartbudject.crmbackend.service;

import ru.smartbudject.crmbackend.model.dto.pointsales.AddPointSalesRequest;


/**
 * Сервис для взаимодействия с торговыми точками.
 */
public interface PointSalesService {

    /**
     * Метод добавления торговой точки.
     * @param addPointSalesRequest
     * @return id
     */
    Long addPointSales(AddPointSalesRequest addPointSalesRequest);

    /**
     * Метод обновление торговой точки.
     * @param addPointSalesRequest
     * @param id
     * @return id
     */
    Long updatePointSales(AddPointSalesRequest addPointSalesRequest, Long id);

}
