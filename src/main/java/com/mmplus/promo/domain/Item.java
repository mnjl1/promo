package com.mmplus.promo.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String ean;

    @Column(name = "item_name")
    private String itemName;

    @Enumerated(EnumType.STRING)
    private Category category;

    public Item() {
    }

    public Item(Long id, String ean, String itemName, Category category) {
        this.id = id;
        this.ean = ean;
        this.itemName = itemName;
        this.category = category;
    }

    //todo fix lombok getters/setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id) &&
                Objects.equals(ean, item.ean) &&
                Objects.equals(itemName, item.itemName) &&
                category == item.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ean, itemName, category);
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", ean='" + ean + '\'' +
                ", itemName='" + itemName + '\'' +
                ", category=" + category +
                '}';
    }
}
