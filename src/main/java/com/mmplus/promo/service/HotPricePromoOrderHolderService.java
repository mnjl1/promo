package com.mmplus.promo.service;

import com.mmplus.promo.domain.activity.HotPricePromoOrderHolder;

import java.util.List;
import java.util.Optional;

public interface HotPricePromoOrderHolderService {

    Optional<HotPricePromoOrderHolder> findById(Long id);

    void saveOrUpdate(HotPricePromoOrderHolder hotPricePromoOrderHolder);

    List<HotPricePromoOrderHolder> findAll();
}
