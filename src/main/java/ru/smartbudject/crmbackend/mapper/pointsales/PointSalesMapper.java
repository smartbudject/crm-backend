package ru.smartbudject.crmbackend.mapper.pointsales;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import ru.smartbudject.crmbackend.model.dto.pointsales.AddPointSalesRequest;
import ru.smartbudject.crmbackend.model.dto.pointsales.PointSalesDTO;
import ru.smartbudject.crmbackend.model.entity.PointSales;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PointSalesMapper {

    PointSales mapAdd(AddPointSalesRequest request);

    @Mapping(target = "version", constant = "1")
    PointSales map(PointSalesDTO pointSalesDTO);
}
