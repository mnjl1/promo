package com.mmplus.promo.controller;

import com.mmplus.promo.data.CompanyRepository;
import com.mmplus.promo.domain.registerforms.CompanyRegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager/registerCompany")
public class CompanyRegistrationController {

    private CompanyRepository companyRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public CompanyRegistrationController(CompanyRepository companyRepository, PasswordEncoder passwordEncoder) {
        this.companyRepository = companyRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String showCompanyRegistrationForm(){
        return "registerCompany";
    }

    @PostMapping
    public String processCompanyRegistration(CompanyRegistrationForm companyRegistrationForm
                                             ){
        companyRepository.save(companyRegistrationForm
                                .toCompany(passwordEncoder));
        return "redirect:/";
    }
}
