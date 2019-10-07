package com.mmplus.promo.repository;

import com.mmplus.promo.domain.schedule.HotPricePromoSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotPricePromoScheduleRepository extends JpaRepository<HotPricePromoSchedule, Long> {
}
