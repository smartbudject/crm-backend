package ru.smartbudject.crmbackend.model.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderMenuItemDto {

    Long id;
    MenuItemDTO menuItemDTO;
    Integer quantity;


}
