package ru.smartbudjet.crm_for_catering.model.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
@Table(name = "combo")
public class Combo extends AbstractEntity {

    @Column(name = "combo_name")
    private String comboName;

    private Double price;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(name = "combo_recipe",
            joinColumns = {@JoinColumn(name = "combo", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "recipe", referencedColumnName = "id")})
    private Set<Recipe> recipes = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(name = "combo_product",
            joinColumns = {@JoinColumn(name = "combo", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "product", referencedColumnName = "id")})
    private Set<Product> products = new HashSet<>();

    @ManyToOne
    private Account account;

    @ManyToOne
    private PointSales pointSales;


    public void setRecipes(Set<Recipe> recipes) {
        if (recipes != null) {
            if (this.recipes == null) {
                this.recipes = recipes;
            } else {
                this.recipes.clear();
                this.recipes.addAll(recipes);
            }
        }
    }


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
