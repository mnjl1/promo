package com.mmplus.promo.domain.activity;

import com.mmplus.promo.domain.Item;
import com.mmplus.promo.domain.schedule.PromoSchedule;
import com.mmplus.promo.utils.Constants;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class PromoOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, insertable = false)
    private Long id;

    private Date createdAt;

    @ManyToMany(targetEntity = Item.class)
    @Size(min = 1, message = Constants.MESSAGE_COLLECTION)
    private List<Item> items;

    @PrePersist
    void createdAte(){
        this.createdAt = new Date();
    }
}
