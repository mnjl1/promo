package com.mmplus.promo.controller;

import com.mmplus.promo.data.PromoOrderHolderRepository;
import com.mmplus.promo.domain.PromoOrderHolder;
import com.mmplus.promo.domain.profiles.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
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

    private PromoOrderHolderRepository promoOrderHolderRepository;

    @GetMapping("/current")
    public String orderForm(){
//        model.addAttribute("promoOrderHolder", new PromoOrderHolder());
        return "orderHolderForm";
    }

    @PostMapping
    public String processOrderHolder(@Valid PromoOrderHolder promoOrderHolder, Errors errors,
                                     SessionStatus sessionStatus,
                                     @AuthenticationPrincipal User user){
        if (errors.hasErrors()){
            return "orderHolderForm";
        }
        promoOrderHolderRepository.save(promoOrderHolder);

        promoOrderHolder.setUser(user);
        sessionStatus.setComplete();

        return "redirect:/";
    }
}
