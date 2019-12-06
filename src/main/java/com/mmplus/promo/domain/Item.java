package com.mmplus.promo.domain;

import com.mmplus.promo.domain.profiles.Company;
import com.mmplus.promo.repository.ItemRepository;
import com.mmplus.promo.service.ItemService;
import com.mmplus.promo.service.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.*;

import static java.util.Objects.hash;

@Entity
@Table(name = "Items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private int ean;

    @Column(name = "item_name")
    private String itemName;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String stockNumber;

    @ManyToMany(fetch = FetchType.LAZY,
    cascade = {CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "items")
    private Set<Company> companies = new HashSet<>();

    public Item() {
    }

    public Item(int ean, String itemName, Category category,
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

    public int getEan() {
        return ean;
    }

    public void setEan(int ean) {
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
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return ean == item.ean &&
                Objects.equals(itemName, item.itemName) &&
                category == item.category &&
                Objects.equals(stockNumber, item.stockNumber);
    }

    @Override
    public int hashCode() {
        return hash(ean, itemName, category, stockNumber);
    }

}
