package ru.smartbudject.crmbackend.model.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDTO {

    Long id;
    String clientUsername;
    List<OrderMenuItemDto> orderItems = new ArrayList<>();
    Integer orderTotalPrice = 0;

    public void addOrderItem(OrderMenuItemDto orderMenuItemDto){
        orderItems.add(orderMenuItemDto);
    }

}
