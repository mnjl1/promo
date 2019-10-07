package com.mmplus.promo.domain.activity;

import com.mmplus.promo.domain.schedule.HotPricePromoSchedule;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Objects;
import java.util.Set;

@Entity
public class HotPricePromoOrder extends PromoOrder {

    @ManyToOne
    private HotPricePromoSchedule hotPricePromoSchedule;

    public HotPricePromoOrder() {
    }

    public HotPricePromoOrder(HotPricePromoSchedule hotPricePromoSchedule) {
        this.hotPricePromoSchedule = hotPricePromoSchedule;
    }

    public HotPricePromoSchedule getHotPricePromoSchedule() {
        return hotPricePromoSchedule;
    }

    public void setHotPricePromoSchedule(HotPricePromoSchedule hotPricePromoSchedule) {
        this.hotPricePromoSchedule = hotPricePromoSchedule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HotPricePromoOrder)) return false;
        if (!super.equals(o)) return false;
        HotPricePromoOrder that = (HotPricePromoOrder) o;
        return Objects.equals(getHotPricePromoSchedule(), that.getHotPricePromoSchedule());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getHotPricePromoSchedule());
    }

    @Override
    public String toString() {
        return "HotPricePromoOrder{" +
                "hotPricePromoSchedule=" + hotPricePromoSchedule +
                '}';
    }
}
