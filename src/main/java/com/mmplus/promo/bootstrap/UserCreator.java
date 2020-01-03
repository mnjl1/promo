package com.mmplus.promo.bootstrap;

import com.mmplus.promo.domain.schedule.HotPricePromoSchedule;
import com.mmplus.promo.repository.CompanyRepository;
import com.mmplus.promo.repository.HotPricePromoScheduleRepository;
import com.mmplus.promo.repository.UserRepository;
import com.mmplus.promo.domain.profiles.Company;
import com.mmplus.promo.domain.profiles.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;

@Component
public class UserCreator {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private HotPricePromoScheduleRepository pricePromoScheduleRepository;

    @PostConstruct
    public void init(){

        userRepository.save(new User(1L,"d", passwordEncoder.encode("111")));
        pricePromoScheduleRepository.save(new HotPricePromoSchedule(LocalDate.now(), LocalDate.now().plusDays(14)));
//        companyRepository.save(new Company(2L,"fff",
//                passwordEncoder.encode("111"),
//                "Ді-Дрейд",
//                "К-630",
//                "di@ukr.net"));
//
    }
}
