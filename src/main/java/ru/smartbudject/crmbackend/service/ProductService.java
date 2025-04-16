package ru.smartbudject.crmbackend.service;

import ru.smartbudject.crmbackend.model.dto.product.AddProductRequest;


public interface ProductService {

    Long addProduct(AddProductRequest addProductRequest);

}
