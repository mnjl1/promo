package com.mmplus.promo.bootstrap;

import com.mmplus.promo.domain.schedule.HotPricePromoSchedule;
import com.mmplus.promo.repository.HotPricePromoScheduleRepository;
import com.mmplus.promo.repository.UserRepository;
import com.mmplus.promo.domain.profiles.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Component
public class UserCreator {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private HotPricePromoScheduleRepository pricePromoScheduleRepository;

    @PostConstruct
    public void init(){

        userRepository.save(new User(1L,"d", passwordEncoder.encode("111")));
        final HotPricePromoSchedule hotPricePromoSchedule = new HotPricePromoSchedule();
        hotPricePromoSchedule.setStartDate(LocalDate.now());
        hotPricePromoSchedule.setEndDate(LocalDate.now().plusDays(14));
        pricePromoScheduleRepository.save(hotPricePromoSchedule);
    }
}