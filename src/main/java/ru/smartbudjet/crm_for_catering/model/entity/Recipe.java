package ru.smartbudjet.crm_for_catering.model.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "recipe")
public class Recipe extends AbstractEntity {

    private String name;
    private Double price;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(name = "recipe_product",
            joinColumns = {@JoinColumn(name = "recipe", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "product", referencedColumnName = "id")})
    private Set<Product> products = new HashSet<>();


    public void setProducts(Set<Product> products) {
        if (products != null) {
            if (this.products == null) {
                this.products = products;
            } else {
                this.products.clear();
                this.products.addAll(products);
            }
        }
    }

}
