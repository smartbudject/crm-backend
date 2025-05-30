package ru.smartbudject.crmbackend.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.smartbudject.crmbackend.model.dto.OrderDTO;
import ru.smartbudject.crmbackend.model.dto.OrderMenuItemDto;
import ru.smartbudject.crmbackend.model.entity.order.Order;
import ru.smartbudject.crmbackend.model.entity.order.OrderMenuItem;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    private final MenuItemMapper menuItemMapper;

    public OrderDTO mapToDTO(Order order) {

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setClientUsername(order.getClient().getUsername());
        for (OrderMenuItem orderMenuItemEntity : order.getOrderMenuItems()) {
            OrderMenuItemDto orderMenuItemDto = new OrderMenuItemDto();
            orderMenuItemDto.setMenuItemDTO(menuItemMapper.mapToDTO(orderMenuItemEntity.getMenuItem()));
            orderMenuItemDto.setQuantity(orderMenuItemEntity.getQuantity());
            orderDTO.addOrderItem(orderMenuItemDto);
        }

        return orderDTO;
    }





}
