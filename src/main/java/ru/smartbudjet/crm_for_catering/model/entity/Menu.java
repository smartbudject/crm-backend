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
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "menu")
public class Menu extends AbstractEntity {

    @Column(name = "menu_name")
    private String menuName;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(name = "menu_combo",
            joinColumns = {@JoinColumn(name = "menu", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "combo", referencedColumnName = "id")})
    private Set<Combo> combos = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(name = "menu_recipe",
            joinColumns = {@JoinColumn(name = "menu", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "recipe", referencedColumnName = "id")})
    private Set<Recipe> recipes = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(name = "menu_product",
            joinColumns = {@JoinColumn(name = "menu", referencedColumnName = "id")},
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


    public void setCombos(Set<Combo> combos) {
        if (combos != null) {
            if (this.combos == null) {
                this.combos = combos;
            } else {
                this.combos.clear();
                this.combos.addAll(combos);
            }
        }
    }

}
