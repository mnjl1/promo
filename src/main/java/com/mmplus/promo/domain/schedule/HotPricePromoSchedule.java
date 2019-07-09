package com.mmplus.promo.domain.schedule;

import com.mmplus.promo.domain.activity.HotPricePromoOrder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
public class HotPricePromoSchedule extends PromoSchedule {

    @OneToMany
    private List<HotPricePromoOrder> hotPricePromoOrders;
}
