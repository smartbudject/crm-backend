package ru.smartbudjet.crm_for_catering.model.entity;

import java.util.Set;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order")
public class Order extends AbstractEntity {

    @ManyToOne
    private OrderStatus status;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(name = "order_item",
            joinColumns = {@JoinColumn(name = "order", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "item", referencedColumnName = "id")})
    private Set<OrderItem> items;

    @ManyToOne
    private Account account;

    @ManyToOne
    private PointSales pointSales;


    public void setItems(Set<OrderItem> items) {
        if (items != null) {
            if (this.items == null) {
                this.items = items;
            } else {
                this.items.clear();
                this.items.addAll(items);
            }
        }
    }

}
