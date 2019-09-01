package com.mmplus.promo.repository;

import com.mmplus.promo.domain.activity.PromoOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromoOrderRepository extends CrudRepository<PromoOrder, Long> {
}
