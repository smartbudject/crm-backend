package ru.smartbudject.crmbackend.model.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MenuDTO {

    Long id;
    String name;
    String pictureFileName;


}
