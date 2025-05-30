package ru.smartbudject.crmbackend.service.menu;

import ch.qos.logback.core.util.StringUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.smartbudject.crmbackend.mapper.MenuItemMapper;
import ru.smartbudject.crmbackend.mapper.OrderMapper;
import ru.smartbudject.crmbackend.model.dto.MenuItemDTO;
import ru.smartbudject.crmbackend.model.dto.OrderDTO;
import ru.smartbudject.crmbackend.model.dto.OrderMenuItemDto;
import ru.smartbudject.crmbackend.model.entity.Account;
import ru.smartbudject.crmbackend.model.entity.MenuItem;
import ru.smartbudject.crmbackend.model.entity.order.Order;
import ru.smartbudject.crmbackend.model.entity.order.OrderMenuItem;
import ru.smartbudject.crmbackend.repository.AccountRepository;
import ru.smartbudject.crmbackend.repository.MenuItemRepository;
import ru.smartbudject.crmbackend.repository.OrderMenuItemRepository;
import ru.smartbudject.crmbackend.repository.OrderRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderService {

    final OrderRepository orderRepository;
    final OrderMenuItemRepository orderMenuItemRepository;
    final MenuItemRepository menuItemRepository;
    final AccountRepository accountRepository;
    final OrderMapper orderMapper;
    final MenuItemMapper menuItemMapper;

    public OrderDTO saveOrder(OrderDTO orderDto) throws ServiceProcessException {
        if (orderDto.getOrderItems().isEmpty() || StringUtil.isNullOrEmpty(orderDto.getClientUsername()))
            throw new ServiceProcessException("order is empty or wrong username!");

        Order order = new Order();
        //search and add client to order
        Optional<Account> client = accountRepository.findByUsername(orderDto.getClientUsername());
        if (client.isEmpty())
            throw new ServiceProcessException(String.format
                    ("Account with username does not exists!",
                            orderDto.getClientUsername()));
        order.setClient(client.get());
        //creating menuitem and adding it to order
        for (OrderMenuItemDto orderItemDTO : orderDto.getOrderItems()) {
            MenuItemDTO menuItemDTO = orderItemDTO.getMenuItemDTO();
            Optional<MenuItem> menuItem = menuItemRepository.findById(menuItemDTO.getId());
            if (menuItem.isEmpty())
                throw new ServiceProcessException(
                        String.format("menu item  (id-%d name-%s) does not exists",
                                menuItemDTO.getId(),
                                menuItemDTO.getName()));
            order.addMenuItem(new OrderMenuItem(order, menuItem.get(), orderItemDTO.getQuantity()));
        }
        Order savedOrder = orderRepository.save(order);
        orderDto.setId(savedOrder.getId());
        return orderDto;
    }

    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::mapToDTO)
                .peek(e-> e.setOrderTotalPrice(calculateOrderTotalPrice(e)))
                .collect(Collectors.toList());
    }

    public List<OrderDTO> getAllUserOrder(Long userId){
        return orderRepository.findAllByClientId(userId)
                .stream()
                .map(orderMapper::mapToDTO)
                .peek(e-> e.setOrderTotalPrice(calculateOrderTotalPrice(e)))
                .collect(Collectors.toList());
    }

    public void addOrderItem(OrderDTO orderDTO, Long menuItemId, Integer quantity) throws ServiceProcessException {
        Optional<MenuItem> menuItemOptional = menuItemRepository.findById(menuItemId);
        if (menuItemOptional.isEmpty()) {
            throw new ServiceProcessException("Not menuItem with id " + menuItemId);
        }
        Optional<OrderMenuItemDto> existingMenuItemOrder = getExistingOrderMenuItem(orderDTO, menuItemId);
        if (existingMenuItemOrder.isPresent()) {
            OrderMenuItemDto orderMenuItemDto = existingMenuItemOrder.get();
            orderMenuItemDto.setQuantity(orderMenuItemDto.getQuantity() + quantity);
            return;
        }


        MenuItemDTO menuItemDTO = menuItemMapper.mapToDTO(menuItemOptional.get());
        OrderMenuItemDto orderMenuItemDto = new OrderMenuItemDto();
        orderMenuItemDto.setMenuItemDTO(menuItemDTO);
        orderMenuItemDto.setQuantity(quantity);
        orderDTO.addOrderItem(orderMenuItemDto);
    }

    private Optional<OrderMenuItemDto> getExistingOrderMenuItem(OrderDTO orderDTO, Long menuItemId) {
        return orderDTO.getOrderItems().stream()
                .filter(e -> e.getMenuItemDTO().getId().equals(menuItemId))
                .findFirst();
    }

    public Integer calculateOrderTotalPrice(OrderDTO orderDTO) {
        return orderDTO.getOrderItems().
                stream()
                .map(e-> e.getMenuItemDTO().getPrice() * e.getQuantity())
                .reduce(Integer::sum).orElse(0);
    }


    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
