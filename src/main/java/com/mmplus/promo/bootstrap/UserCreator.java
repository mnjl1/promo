package com.mmplus.promo.bootstrap;

import com.mmplus.promo.repository.CompanyRepository;
import com.mmplus.promo.repository.UserRepository;
import com.mmplus.promo.domain.profiles.Company;
import com.mmplus.promo.domain.profiles.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class UserCreator {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init(){

        userRepository.save(new User("d", passwordEncoder.encode("111")));

        companyRepository.save(new Company("fff", passwordEncoder.encode("111"), "Ferrero",
                "f@ukr.net", 123456));

    }

}
