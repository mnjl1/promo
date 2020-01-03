package com.mmplus.promo.controller;

import com.mmplus.promo.domain.activity.HotPricePromoOrder;
import com.mmplus.promo.domain.activity.HotPricePromoOrderHolder;
import com.mmplus.promo.domain.profiles.Company;
import com.mmplus.promo.domain.schedule.HotPricePromoSchedule;
import com.mmplus.promo.repository.CompanyRepository;
import com.mmplus.promo.domain.Category;
import com.mmplus.promo.domain.Item;
import com.mmplus.promo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@SessionAttributes("hotPricePromoOrder")
@RequestMapping("/company")
public class HotPricePromoOrderController {

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
   private UserRepositoryUserDetailsService userDetailsService;

    @Autowired
    private CompanyRepositoryUserDetailsService companyRepositoryUserDetailsService;

    @Autowired
    private HotPricePromoScheduleService hotPricePromoScheduleService;

    private final ItemService itemService;
    private final HotPricePromoOrderService hotPricePromoOrderService;
    private final CompanyService companyService;

    public HotPricePromoOrderController(ItemService itemService,
                                        HotPricePromoOrderService hotPricePromoOrderService,
                                        CompanyService companyService) {
        this.itemService = itemService;
        this.hotPricePromoOrderService = hotPricePromoOrderService;
        this.companyService = companyService;
    }

    @RequestMapping("/hot-price-schedule-list")
    public String getHotPriceScheduleList(Model model){
        model.addAttribute("hotPricePromoScheduleList", hotPricePromoScheduleService.findAll());
        return "hot-price-schedule-list";
    }

    @GetMapping("/place-hot-price-promo-order/{id}")
    public String showOrderFormAndPassId(Model model, @PathVariable (value = "id") Long id){
        HotPricePromoOrder hotPricePromoOrder = new HotPricePromoOrder();
        HotPricePromoSchedule promoSchedule = hotPricePromoScheduleService.findById(id).get();

        //test print
        String username = userDetailsService.getPrincipal();
        System.out.println("Principal class: " +username);
        Company company = companyRepositoryUserDetailsService.findCompanyByUserName(username);
        System.out.println("Company: " +company.getCompanyName() + " " +company.getContractNumber());

        Set<Item> items = company.getItems();

        Category[] categories = Category.values();

        for (Category category: categories
        ) {

            model.addAttribute(category.getCategoryValue().toLowerCase(),
                    filterByCategory(items, category.getCategoryValue()));
        }
        System.out.println("Company after categories: " +company.getCompanyName());

        model.addAttribute("company", company);
        model.addAttribute("hotPricePromoOrder", hotPricePromoOrder);
        model.addAttribute("promoSchedule", promoSchedule);

        return "hot-price-promo-order";
    }

    @GetMapping("/place-hot-price-promo-order")
    public String showOrderForm(Model model, HotPricePromoOrder hotPricePromoOrder){

        model.addAttribute("hotPricePromoOrder", hotPricePromoOrder);

        return "hot-price-promo-order";
    }

    @ModelAttribute(name = "hotPricePromoOrder")
    public HotPricePromoOrderHolder promoOrderHolder(){
        return new HotPricePromoOrderHolder();
    }

    @ModelAttribute(name = "hotPricePromoOrder")
    public HotPricePromoOrder hotPricePromoOrder(){
        return new HotPricePromoOrder();
    }

    @PostMapping
    public String processHotPricePromoOrderForm(@Valid HotPricePromoOrder hotPricePromoOrder, Errors errors,
                                                @ModelAttribute HotPricePromoOrderHolder hotPricePromoOrderHolder){

        if (errors.hasErrors()){
            return "hot-price-promo-order";
        }

        HotPricePromoOrder saveHotPricePromoOrder = hotPricePromoOrderService
                .saveOrUpdateHotPricePromoOrder(hotPricePromoOrder);
        promoOrderHolder().addHotPricePromoOrder(saveHotPricePromoOrder);

        return "redirect:/orders/current";
    }

    private List<Item> filterByCategory(Set<Item> items, String category){
        List<Item> filteredItems = items.stream()
                .filter(p->category.equals(p.getCategory()
                .getCategoryValue()))
                .collect(Collectors.toList());
        return filteredItems;
    }
}
