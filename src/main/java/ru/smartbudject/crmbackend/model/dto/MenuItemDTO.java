package ru.smartbudject.crmbackend.model.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MenuItemDTO {

    Long id;
    String name;
    String description;
    Integer price = 0;
    String bju;
    String pictureFileName;
}
