package com.mmplus.promo.domain.activity;

import com.mmplus.promo.domain.schedule.HotPricePromoSchedule;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class HotPricePromoOrder extends PromoOrder {

    @ManyToOne(fetch = FetchType.LAZY)
    private HotPricePromoSchedule hotPricePromoSchedule;

    public HotPricePromoOrder() {
    }

    public HotPricePromoOrder(HotPricePromoSchedule hotPricePromoSchedule){
        super();
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
