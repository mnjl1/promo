package com.mmplus.promo.controller;

import com.mmplus.promo.domain.activity.HotPricePromoOrderHolder;
import com.mmplus.promo.domain.profiles.Company;
import com.mmplus.promo.service.HotPricePromoOrderHolderService;
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
@SessionAttributes("hotPricePromoOrderHolder")
@RequestMapping("/orders")
public class HotPricePromoOrderHolderController {

    private final HotPricePromoOrderHolderService hotPricePromoOrderHolderService;

    public HotPricePromoOrderHolderController(HotPricePromoOrderHolderService hotPricePromoOrderHolderService) {
        this.hotPricePromoOrderHolderService = hotPricePromoOrderHolderService;
    }

    @GetMapping("/current")
    public String orderForm(Model model){
        model.addAttribute("hotPricePromoOrderHolder", new HotPricePromoOrderHolder());
        return "hotPricePromoOrderHolderForm";
    }

    @PostMapping
    public String processOrderHolder(@Valid HotPricePromoOrderHolder hotPricePromoOrderHolder, Errors errors,
                                     SessionStatus sessionStatus,
                                     @AuthenticationPrincipal Company company){
        if (errors.hasErrors()){
            return "hotPricePromoOrderHolderForm";
        }
        hotPricePromoOrderHolderService.saveOrUpdate(hotPricePromoOrderHolder);

        hotPricePromoOrderHolder.setCompany(company);
        sessionStatus.setComplete();

        return "redirect:/";
    }
}
