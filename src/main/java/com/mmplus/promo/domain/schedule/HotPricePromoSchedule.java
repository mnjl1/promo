package com.mmplus.promo.domain.schedule;

import com.mmplus.promo.domain.activity.HotPricePromoOrder;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class HotPricePromoSchedule extends PromoSchedule {

    @OneToMany(mappedBy = "hotPricePromoSchedule")
    private List<HotPricePromoOrder> hotPricePromoOrders;


    public HotPricePromoSchedule() {
    }



    public List<HotPricePromoOrder> getHotPricePromoOrders() {
        return hotPricePromoOrders;
    }

    public void setHotPricePromoOrders(List<HotPricePromoOrder> hotPricePromoOrders) {
        this.hotPricePromoOrders = hotPricePromoOrders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HotPricePromoSchedule)) return false;
        if (!super.equals(o)) return false;
        HotPricePromoSchedule schedule = (HotPricePromoSchedule) o;
        return Objects.equals(getHotPricePromoOrders(), schedule.getHotPricePromoOrders());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getHotPricePromoOrders());
    }

    @Override
    public String toString() {
        return "HotPricePromoSchedule{" +
                "hotPricePromoOrders=" + hotPricePromoOrders +
                '}';
    }
}
