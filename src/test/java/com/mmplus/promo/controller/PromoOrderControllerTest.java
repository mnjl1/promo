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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PromoOrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(roles = "COMPANY")
    public void shouldReturnOrderFom() throws Exception{
        this.mockMvc.perform(get("/place-promoOrder"))
                .andExpect(status().isOk())
                .andExpect(view().name("order"));
    }

    @Test
    @WithMockUser(roles = "COMPANY")
    public void processOrderFormTest() throws Exception{
        this.mockMvc.perform(post("/place-promoOrder"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("order"));
    }
}
