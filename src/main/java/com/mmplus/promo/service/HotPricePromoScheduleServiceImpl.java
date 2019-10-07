package com.mmplus.promo.service;

import com.mmplus.promo.domain.schedule.HotPricePromoSchedule;
import com.mmplus.promo.repository.HotPricePromoScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotPricePromoScheduleServiceImpl implements HotPricePromoScheduleService {

    private final HotPricePromoScheduleRepository repository;

    public HotPricePromoScheduleServiceImpl(HotPricePromoScheduleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<HotPricePromoSchedule> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public HotPricePromoSchedule saveOrUpdate(HotPricePromoSchedule hotPricePromoSchedule) {
        return repository.save(hotPricePromoSchedule);
    }

    @Override
    public List<HotPricePromoSchedule> findAll() {
        return repository.findAll();
    }
}
