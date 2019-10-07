package com.mmplus.promo.repository;

import com.mmplus.promo.domain.activity.HotPricePromoOrderHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotPricePromoOrderHolderRepository extends JpaRepository<HotPricePromoOrderHolder, Long> {
}
