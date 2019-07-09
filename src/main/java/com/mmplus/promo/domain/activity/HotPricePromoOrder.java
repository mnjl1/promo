package com.mmplus.promo.domain.activity;

import com.mmplus.promo.domain.schedule.HotPricePromoSchedule;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@Entity
public class HotPricePromoOrder extends PromoOrder {

}
