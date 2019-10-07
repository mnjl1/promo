package com.mmplus.promo.service;

import com.mmplus.promo.domain.activity.HotPricePromoOrder;

import java.util.List;
import java.util.Optional;

public interface HotPricePromoOrderService {

    Optional<HotPricePromoOrder> findById(Long id);

    HotPricePromoOrder saveOrUpdateHotPricePromoOrder(HotPricePromoOrder hotPricePromoOrder);

    List<HotPricePromoOrder> findAll();
}
