package ru.smartbudject.crmbackend.model.entity.order;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import ru.smartbudject.crmbackend.model.entity.Account;
import ru.smartbudject.crmbackend.model.entity.MenuItem;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "order_new")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    Account client;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<OrderMenuItem> orderMenuItems = new HashSet<>();

    public void addMenuItem(OrderMenuItem orderMenuItem){
        orderMenuItem.setOrder(this);
        orderMenuItems.add(orderMenuItem);
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Order order = (Order) o;
//        return id != null && id.equals(order.id);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id);
//    }
}
