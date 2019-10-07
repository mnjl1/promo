package com.mmplus.promo.domain;

import com.mmplus.promo.domain.profiles.Company;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
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

    private String stockNumber;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "items")
    private Set<Company> companies = new HashSet<>();

    public Item() {
    }

    public Item(String ean, String itemName, Category category,
                String stockNumber,
                Set<Company> companies) {
        this.ean = ean;
        this.itemName = itemName;
        this.category = category;
        this.stockNumber = stockNumber;
        this.companies = companies;
    }

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

    public String getStockNumber() {
        return stockNumber;
    }

    public void setStockNumber(String stockNumber) {
        this.stockNumber = stockNumber;
    }

    @ManyToMany
    public Set<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(Set<Company> companies) {
        this.companies = companies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return Objects.equals(getId(), item.getId()) &&
                Objects.equals(getEan(), item.getEan()) &&
                Objects.equals(getItemName(), item.getItemName()) &&
                getCategory() == item.getCategory() &&
                Objects.equals(getStockNumber(), item.getStockNumber()) &&
                Objects.equals(getCompanies(), item.getCompanies());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEan(), getItemName(), getCategory(), getStockNumber(), getCompanies());
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", ean='" + ean + '\'' +
                ", itemName='" + itemName + '\'' +
                ", category=" + category +
                ", stockNumber='" + stockNumber + '\'' +
                ", companies=" + companies +
                '}';
    }
}
