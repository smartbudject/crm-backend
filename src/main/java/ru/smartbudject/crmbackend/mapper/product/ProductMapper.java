package ru.smartbudject.crmbackend.mapper.product;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import ru.smartbudject.crmbackend.mapper.pointsales.PointSalesMapper;
import ru.smartbudject.crmbackend.model.dto.product.AddProductRequest;
import ru.smartbudject.crmbackend.model.entity.Product;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, imports = PointSalesMapper.class)
public interface ProductMapper {

    Product mapAddProduct(AddProductRequest addProductRequest);
}
