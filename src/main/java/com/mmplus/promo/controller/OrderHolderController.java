package com.mmplus.promo.controller;

import com.mmplus.promo.repository.PromoOrderHolderRepository;
import com.mmplus.promo.domain.PromoOrderHolder;
import com.mmplus.promo.domain.profiles.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Controller
@SessionAttributes("promoOrderHolder")
@RequestMapping("/orders")
public class OrderHolderController {

    @Autowired
    private PromoOrderHolderRepository promoOrderHolderRepository;

    @GetMapping("/current")
    public String orderForm(Model model){
        model.addAttribute("promoOrderHolder", new PromoOrderHolder());
        return "orderHolderForm";
    }

    @PostMapping
    public String processOrderHolder(@Valid PromoOrderHolder promoOrderHolder, Errors errors,
                                     SessionStatus sessionStatus,
                                     @AuthenticationPrincipal Company company){
        if (errors.hasErrors()){
            return "orderHolderForm";
        }
        promoOrderHolderRepository.save(promoOrderHolder);

        promoOrderHolder.setCompany(company);
        sessionStatus.setComplete();

        return "redirect:/";
    }
}
