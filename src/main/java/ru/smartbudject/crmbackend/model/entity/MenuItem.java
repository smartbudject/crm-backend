package ru.smartbudject.crmbackend.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "menu_item")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "name")
    String name;
    @Column(name = "description")
    String description;
    @Column(name = "price")
    Integer price = 0;
    @Column(name = "bju")
    String bju;
    @Column(name = "picture_file_name")
    String pictureFileName;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // Allows only writing from JSON
    @ManyToOne(fetch = FetchType.LAZY)
    Menu menu;

    public void setMenu(Menu menu){
        this.menu = menu;
    }
}
