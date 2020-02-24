package com.mmplus.promo.controller;

import com.mmplus.promo.domain.activity.HotPricePromoOrder;
import com.mmplus.promo.domain.schedule.HotPricePromoSchedule;
import com.mmplus.promo.service.HotPricePromoScheduleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/mmplus")
public class HotPriceScheduleController {
    private final HotPricePromoScheduleService hotPricePromoScheduleService;

    public HotPriceScheduleController(HotPricePromoScheduleService hotPricePromoScheduleService) {
        this.hotPricePromoScheduleService = hotPricePromoScheduleService;
    }

    @RequestMapping("/hot-price-schedule-form")
    public String createSchedule(Model model){
        HotPricePromoSchedule schedule = new HotPricePromoSchedule();
        model.addAttribute("schedule", schedule);
        return "hot-price-schedule-form";
    }

    @RequestMapping("/process-hot-price-schedule-form")
    public String processHotPriceScheduleFor(Model model, HotPricePromoSchedule schedule){
        model.addAttribute("schedule", schedule);
        hotPricePromoScheduleService.saveOrUpdate(schedule);
        return  "redirect:/company/hot-price-schedule-list";
    }

    @GetMapping("/process-hot-price-promo-orders/{id}")
    public String processHotPricePromoOrdersPerPeriod(Model model, @PathVariable(value = "id") Long id){
        Optional<HotPricePromoSchedule> currentHotPricePromoSchedule = hotPricePromoScheduleService
                .findById(id);
        List<HotPricePromoOrder> hotPricePromoOrders =
                hotPricePromoScheduleService.findById(id)
                .get()
                .getHotPricePromoOrders();
        model.addAttribute("currentHotPricePromoSchedule", currentHotPricePromoSchedule);
        model.addAttribute("hotPricePromoOrders", hotPricePromoOrders);
        return "hot-price-promo-orders-list-per-date";
    }
}
