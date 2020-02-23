package com.mmplus.promo.controller;

import com.mmplus.promo.domain.activity.HotPricePromoOrder;
import com.mmplus.promo.domain.profiles.Company;
import com.mmplus.promo.domain.schedule.HotPricePromoSchedule;
import com.mmplus.promo.domain.Category;
import com.mmplus.promo.domain.Item;
import com.mmplus.promo.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/company")
public class HotPricePromoOrderController {

    private final UserRepositoryUserDetailsService userDetailsService;
    private final CompanyRepositoryUserDetailsService companyRepositoryUserDetailsService;
    private final HotPricePromoScheduleService hotPricePromoScheduleService;
    private final HotPricePromoOrderService hotPricePromoOrderService;

    public HotPricePromoOrderController(UserRepositoryUserDetailsService userDetailsService,
                                        CompanyRepositoryUserDetailsService companyRepositoryUserDetailsService,
                                        HotPricePromoScheduleService hotPricePromoScheduleService,
                                        HotPricePromoOrderService hotPricePromoOrderService) {
        this.userDetailsService = userDetailsService;
        this.companyRepositoryUserDetailsService = companyRepositoryUserDetailsService;
        this.hotPricePromoScheduleService = hotPricePromoScheduleService;
        this.hotPricePromoOrderService = hotPricePromoOrderService;
    }

    @RequestMapping("/hot-price-schedule-list")
    public String getHotPriceScheduleList(Model model){
        model.addAttribute("hotPricePromoScheduleList", hotPricePromoScheduleService.findAll());
        return "hot-price-schedule-list";
    }

    @GetMapping("/place-hot-price-promo-order/{id}")
    public String showOrderFormAndPassId(Model model, @PathVariable (value = "id") Long id){
        HotPricePromoOrder hotPricePromoOrder = new HotPricePromoOrder();
        HotPricePromoSchedule hotPricePromoSchedule = hotPricePromoScheduleService
                .findById(id).get();

        String username = userDetailsService.getPrincipal();
        Company company = companyRepositoryUserDetailsService.findCompanyByUserName(username);

        Set<Item> items = company.getItems();

        Category[] categories = Category.values();

        for (Category category: categories
        ) {

            model.addAttribute(category.getCategoryValue().toLowerCase(),
                    filterByCategory(items, category.getCategoryValue().toLowerCase()));
        }

        model.addAttribute("company", company);
        model.addAttribute("hotPricePromoOrder", hotPricePromoOrder);
        model.addAttribute("hotPricePromoSchedule", hotPricePromoSchedule);

        return "hot-price-promo-order";
    }

    @PostMapping("/process-hot-price-promo-order")
    public String showOrderForm(Model model, HotPricePromoOrder hotPricePromoOrder){

        model.addAttribute("hotPricePromoOrder", hotPricePromoOrder);
        hotPricePromoOrderService.saveOrUpdateHotPricePromoOrder(hotPricePromoOrder);

        return "redirect:/";
    }

    private List<Item> filterByCategory(Set<Item> items, String category){
        return items.stream()
                .filter(p->category.equals(p.getCategory()
                        .getCategoryValue().toLowerCase()))
                .collect(Collectors.toList());
    }
}
