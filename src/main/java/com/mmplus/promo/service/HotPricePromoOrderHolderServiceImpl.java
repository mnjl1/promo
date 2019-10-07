package com.mmplus.promo.service;

import com.mmplus.promo.domain.activity.HotPricePromoOrderHolder;
import com.mmplus.promo.repository.HotPricePromoOrderHolderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotPricePromoOrderHolderServiceImpl implements HotPricePromoOrderHolderService {

    private final HotPricePromoOrderHolderRepository hotPricePromoOrderHolderRepository;

    public HotPricePromoOrderHolderServiceImpl(HotPricePromoOrderHolderRepository hotPricePromoOrderHolderRepository) {
        this.hotPricePromoOrderHolderRepository = hotPricePromoOrderHolderRepository;
    }

    @Override
    public Optional<HotPricePromoOrderHolder> findById(Long id) {
        return hotPricePromoOrderHolderRepository.findById(id);
    }

    @Override
    public void saveOrUpdate(HotPricePromoOrderHolder hotPricePromoOrderHolder) {
        hotPricePromoOrderHolderRepository.save(hotPricePromoOrderHolder);
    }

    @Override
    public List<HotPricePromoOrderHolder> findAll() {
        return hotPricePromoOrderHolderRepository.findAll();
    }
}
