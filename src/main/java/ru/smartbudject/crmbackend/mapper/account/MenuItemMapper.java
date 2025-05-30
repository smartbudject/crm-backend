package ru.smartbudject.crmbackend.mapper;

import org.springframework.stereotype.Component;
import ru.smartbudject.crmbackend.model.dto.MenuItemDTO;
import ru.smartbudject.crmbackend.model.entity.MenuItem;

@Component
public class MenuItemMapper {

    public MenuItemDTO mapToDTO(MenuItem menuItemEntity){
        MenuItemDTO menuItemDTO = new MenuItemDTO();
        menuItemDTO.setId(menuItemEntity.getId());
        menuItemDTO.setName(menuItemEntity.getName());
        menuItemDTO.setDescription(menuItemEntity.getDescription());
        menuItemDTO.setBju(menuItemEntity.getBju());
        menuItemDTO.setPrice(menuItemEntity.getPrice());
        menuItemDTO.setPictureFileName(menuItemEntity.getPictureFileName());
        return menuItemDTO;
    }

}
