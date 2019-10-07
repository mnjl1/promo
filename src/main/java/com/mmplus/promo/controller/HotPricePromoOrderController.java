package com.mmplus.promo.controller;

import com.mmplus.promo.domain.activity.HotPricePromoOrder;
import com.mmplus.promo.domain.activity.HotPricePromoOrderHolder;
import com.mmplus.promo.domain.profiles.Company;
import com.mmplus.promo.repository.CompanyRepository;
import com.mmplus.promo.domain.Category;
import com.mmplus.promo.domain.Item;
import com.mmplus.promo.service.HotPricePromoOrderService;
import com.mmplus.promo.service.ItemService;
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
@RequestMapping("/place-hot-price-promo-order")
public class HotPricePromoOrderController {

    @Autowired
    private CompanyRepository companyRepository;

    private final ItemService itemService;
    private final HotPricePromoOrderService hotPricePromoOrderService;

    public HotPricePromoOrderController(ItemService itemService, HotPricePromoOrderService hotPricePromoOrderService) {
        this.itemService = itemService;
        this.hotPricePromoOrderService = hotPricePromoOrderService;
    }

    @GetMapping
    public String showOrderForm(Model model){

        List<Item> items = new ArrayList<>();

        itemService.findAll().forEach(items::add);

        Category[] categories = Category.values();

        for (Category category: categories
             ) {
            model.addAttribute(category.getCategoryValue().toLowerCase(),
                    filterByCategory(items, category.getCategoryValue()));
        }

        model.addAttribute("hotPricePromoOrder", new HotPricePromoOrder());

        return "hotPricePromoOrder";
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
            return "hotPricePromoOrder";
        }

        HotPricePromoOrder saveHotPricePromoOrder = hotPricePromoOrderService
                .saveOrUpdateHotPricePromoOrder(hotPricePromoOrder);
        promoOrderHolder().addHotPricePromoOrder(saveHotPricePromoOrder);

        return "redirect:/orders/current";
    }

    private List<Item> filterByCategory(List<Item> items, String category){
        List<Item> filteredItems = items.stream().filter(p->category.equals(p.getCategory().getCategoryValue())).collect(Collectors.toList());
        return filteredItems;
    }
}
