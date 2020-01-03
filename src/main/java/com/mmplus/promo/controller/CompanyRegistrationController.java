package com.mmplus.promo.controller;

import com.mmplus.promo.domain.profiles.Company;
import com.mmplus.promo.domain.profiles.User;
import com.mmplus.promo.repository.CompanyRepository;
import com.mmplus.promo.domain.registerforms.CompanyRegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/manager")
public class CompanyRegistrationController {

    private CompanyRepository companyRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public CompanyRegistrationController(CompanyRepository companyRepository, PasswordEncoder passwordEncoder) {
        this.companyRepository = companyRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/registerCompany/{id}")
    public String showCompanyRegistrationFormById(Model model, @PathVariable(value = "id") Long id){
        User company = companyRepository.findById(id).get();
        model.addAttribute("company", company);
        return "registerCompany";
    }

    @GetMapping("/registerCompany")
    public String showCompanyRegistrationFormBy(Model model, Company company){
        model.addAttribute("company", company);
        return "registerCompany";
    }

    @PostMapping("/process-company-registration")
    public String processCompanyRegistration(Model model, Company company,
                                             CompanyRegistrationForm companyRegistrationForm){

        model.addAttribute("company", companyRegistrationForm.toCompany(passwordEncoder, company));
        companyRepository.save(company);

        return "redirect:/";
    }
}
