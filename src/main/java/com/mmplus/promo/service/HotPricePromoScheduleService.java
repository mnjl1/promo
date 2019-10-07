package com.mmplus.promo.service;

import com.mmplus.promo.domain.schedule.HotPricePromoSchedule;

import java.util.List;
import java.util.Optional;

public interface HotPricePromoScheduleService {

    Optional<HotPricePromoSchedule> findById(Long id);

    HotPricePromoSchedule saveOrUpdate(HotPricePromoSchedule hotPricePromoSchedule);

    List<HotPricePromoSchedule> findAll();
}
