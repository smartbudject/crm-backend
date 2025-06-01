package ru.smartbudject.crmbackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.smartbudject.crmbackend.config.security.UserDetailsImpl;
import ru.smartbudject.crmbackend.model.dto.OrderDTO;
import ru.smartbudject.crmbackend.model.entity.Account;
import ru.smartbudject.crmbackend.service.menu.OrderService;
import ru.smartbudject.crmbackend.service.menu.ServiceProcessException;

import java.util.List;

@Controller
@SessionAttributes("current_order")
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @ModelAttribute("current_order")
    public OrderDTO createOrder() {
        return new OrderDTO();
    }

    @PostMapping("/dropOrder")
    public String dropOrder(
            @RequestParam("menuId") Long menuId,
            SessionStatus status
    ) {
        status.setComplete();
        return String.format("redirect:/menu/menuItem/%d", menuId);
    }

    @PostMapping("/completeOrder")
    public String completeOrder(
            @RequestParam("username") String username,
            @RequestParam("menuId") Long menuId,
            @ModelAttribute("current_order") OrderDTO orderDTO,
            RedirectAttributes redirectAttributes,
            SessionStatus status
    ) {
        try {
            orderDTO.setClientUsername(username);
            orderService.saveOrder(orderDTO);
            redirectAttributes.addFlashAttribute("messages", "Order is confirmed!");
            status.setComplete();  // Cleans up the session attributes
        } catch (ServiceProcessException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return String.format("redirect:/menu/menuItem/%d", menuId);
    }

    @PostMapping("/addOrderItem")
    public String addOrderItem(
            @RequestParam("menuId") Long menuId,
            @RequestParam("menuItemId") Long menuItemId,
            @RequestParam("quantity") Integer quantity,
            @ModelAttribute("current_order") OrderDTO orderDTO,
            RedirectAttributes redirectAttributes
    ) {
        try {
            orderService.addOrderItem(orderDTO, menuItemId, quantity);
        } catch (ServiceProcessException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error"
                    , "Error on order save!");
        } finally {
            if (!orderDTO.getOrderItems().isEmpty()) {
                redirectAttributes.addFlashAttribute("current_order", orderDTO);
                redirectAttributes.addFlashAttribute("order_total_price", orderService.calculateOrderTotalPrice(orderDTO));
            }
        }
        return String.format("redirect:/menu/menuItem/%d", menuId);
    }

    @GetMapping
    public String getOrderPage(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            Model model
    ) {
        if(userDetails == null) return "order";
        Account user = userDetails.getUser();
        List<OrderDTO> orders =
                user.getRole().getName().equalsIgnoreCase("ROLE_ADMIN") ?
                orderService.getAllOrders():
                orderService.getAllUserOrder(user.getId());
        model.addAttribute("orders", orders);
        return "order";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/delete")
    public String deleteOrder(
            @RequestParam("orderId") Long orderId,
            Model model
    ){
        try {
            orderService.deleteOrder(orderId);
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("error", "Error on order delete");
        }
        model.addAttribute("orders", orderService.getAllOrders());
        return "order";
    }

//    @ResponseBody
//    @PostMapping(value = "/create")
//    public ResponseEntity<?> saveOrder(@RequestBody OrderDTO orderDTO) {
//        try {
//            OrderDTO saveOrder = orderService.saveOrder(orderDTO);
//            return ResponseEntity
//                    .ok(saveOrder);
//        } catch (ServiceProcessException e) {
//            return ResponseEntity
//                    .badRequest()
//                    .body(e.getMessage());
//        }
//    }


//    @ResponseBody
//    @PostMapping("/delete")
//    public ResponseEntity<?> deleteMenu(@RequestBody OrderDTO orderDTO) {
//        orderService.deleteOrder(orderDTO.getId());
//        return ResponseEntity
//                .ok().build();
//    }


}


