package ru.smartbudject.crmbackend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.smartbudject.crmbackend.model.dto.MenuDTO;
import ru.smartbudject.crmbackend.model.entity.Menu;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MenuMapper {

    Menu map(final MenuDTO menuDTO);
}
