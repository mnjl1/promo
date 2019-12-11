package com.mmplus.promo.controller;

import com.mmplus.promo.domain.schedule.HotPricePromoSchedule;
import com.mmplus.promo.service.HotPricePromoScheduleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
