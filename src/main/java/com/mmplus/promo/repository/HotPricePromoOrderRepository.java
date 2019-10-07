package com.mmplus.promo.repository;

import com.mmplus.promo.domain.activity.HotPricePromoOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotPricePromoOrderRepository extends JpaRepository<HotPricePromoOrder, Long> {
}
