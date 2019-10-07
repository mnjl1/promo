package com.mmplus.promo.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HotPriceScheduleControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(roles = "USER")
    public void shouldReturnHotPriceScheduleForm() throws Exception{
        this.mockMvc
                .perform(get("/mmplus/hot-price-schedule-form"))
                .andExpect(status().isOk())
                .andExpect(view().name("hot-price-schedule-form"));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void shouldProcessHotPriceScheduleForm() throws Exception{
        this.mockMvc
                .perform(post("/mmplus/process-hot-price-schedule-form"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("hot-price-schedule-list"));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void shouldReturnScheduleList() throws Exception{
        this.mockMvc
                .perform(get("/mmplus/hot-price-schedule-list"))
                .andExpect(status().isOk())
                .andExpect(view().name("hot-price-schedule-list"));
    }
}
