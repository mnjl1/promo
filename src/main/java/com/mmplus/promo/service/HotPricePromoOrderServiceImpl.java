package com.mmplus.promo.service;

import com.mmplus.promo.domain.activity.HotPricePromoOrder;
import com.mmplus.promo.repository.HotPricePromoOrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotPricePromoOrderServiceImpl implements HotPricePromoOrderService {

    private final HotPricePromoOrderRepository hotPricePromoOrderRepository;

    public HotPricePromoOrderServiceImpl(HotPricePromoOrderRepository hotPricePromoOrderRepository) {
        this.hotPricePromoOrderRepository = hotPricePromoOrderRepository;
    }

    @Override
    public Optional<HotPricePromoOrder> findById(Long id) {
        return hotPricePromoOrderRepository.findById(id);
    }

    @Override
    public HotPricePromoOrder saveOrUpdateHotPricePromoOrder(HotPricePromoOrder hotPricePromoOrder) {
        hotPricePromoOrderRepository.save(hotPricePromoOrder);
        return hotPricePromoOrder;
    }

    @Override
    public List<HotPricePromoOrder> findAll() {
        return hotPricePromoOrderRepository.findAll();
    }
}
