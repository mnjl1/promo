package com.mmplus.promo.controller;

import com.mmplus.promo.data.ItemRepository;
import com.mmplus.promo.data.PromoOrderRepository;
import com.mmplus.promo.domain.Category;
import com.mmplus.promo.domain.Item;
import com.mmplus.promo.domain.activity.PromoOrder;
import com.mmplus.promo.domain.PromoOrderHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@SessionAttributes("promoOrder")
@RequestMapping("/place-promoOrder")
public class PromoOrderController {


    private final ItemRepository itemRepository;
    private PromoOrderRepository promoOrderRepository;

    @Autowired
    public PromoOrderController(ItemRepository itemRepository,
                                PromoOrderRepository promoOrderRepository) {
        this.itemRepository = itemRepository;
        this.promoOrderRepository = promoOrderRepository;
    }

    @GetMapping
    public String showOrderForm(Model model){

        List<Item> items = new ArrayList<>();

        itemRepository.findAll().forEach(i->items.add(i));

        Category[] categories = Category.values();

        for (Category category: categories
             ) {
            model.addAttribute(category.toString().toLowerCase(), filterByCategory(items, category));
        }

        model.addAttribute("promoOrder", new PromoOrder());

        return "order";
    }

    @ModelAttribute(name = "promoOrderHolder")
    public PromoOrderHolder promoOrderHolder(){
        return new PromoOrderHolder();
    }

    @ModelAttribute(name = "promoOrder")
    public PromoOrder promoOrder(){
        return new PromoOrder();
    }

    @PostMapping
    public String processOrderForm(@Valid PromoOrder promoOrder, Errors errors,
                                   @ModelAttribute PromoOrderHolder promoOrderHolder){

        if (errors.hasErrors()){
            return "order";
        }

        PromoOrder savePromoOrder = promoOrderRepository.save(promoOrder);
        promoOrderHolder().addPromoOrder(savePromoOrder);

        return "redirect:/orders/current";
    }

    private List<Item> filterByCategory(List<Item> items, Category category){
        List<Item> filteredItems = items.stream()
                .filter(p->category.equals(p.getCategory())).collect(Collectors.toList());
        return filteredItems;
    }
}
