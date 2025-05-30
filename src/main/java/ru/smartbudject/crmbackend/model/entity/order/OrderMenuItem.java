package ru.smartbudject.crmbackend.model.entity.order;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import ru.smartbudject.crmbackend.model.entity.MenuItem;

import java.util.Objects;

@Entity
@Table(name = "order_menu_item")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class OrderMenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_item_id")
    MenuItem menuItem;

    @Column(name = "quantity")
    private Integer quantity;

    public OrderMenuItem(Order order, MenuItem menuItem, int quantity) {
        this.order = order;
        this.menuItem = menuItem;
        this.quantity = quantity;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        OrderMenuItem orderMenuItem = (OrderMenuItem) o;
//        return menuItem != null && menuItem.equals(orderMenuItem.menuItem)
//               && order != null && order.equals(orderMenuItem.order);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(order, menuItem);
//    }

}
