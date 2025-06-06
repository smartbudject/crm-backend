package ru.smartbudject.crmbackend.service;

import ru.smartbudject.crmbackend.model.dto.product.AddProductRequest;


/**
 * Сервис взаимодействия с продуктами.
 */
public interface ProductService {

    /**
     * Метод добавления продуктов в торговую точку.
     * @param addProductRequest
     * @return id
     */
    Long addProduct(AddProductRequest addProductRequest);

}
