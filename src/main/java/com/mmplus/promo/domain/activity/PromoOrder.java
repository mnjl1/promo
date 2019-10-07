package com.mmplus.promo.domain.activity;

import com.mmplus.promo.domain.Item;
import com.mmplus.promo.utils.Constants;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class PromoOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date createdAt;

    @ManyToMany(targetEntity = Item.class)
    @Size(min = 1, message = Constants.MESSAGE_COLLECTION)
    private List<Item> items = new ArrayList<>();

    @PrePersist
    void createdAte(){
        this.createdAt = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PromoOrder)) return false;
        PromoOrder that = (PromoOrder) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, items);
    }

    @Override
    public String toString() {
        return "PromoOrder{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", items=" + items +
                '}';
    }
}
