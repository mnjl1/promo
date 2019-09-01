package com.mmplus.promo.config;

import com.mmplus.promo.converter.CategoryEnumFormatter;
import com.mmplus.promo.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private CategoryEnumFormatter categoryEnumFormatter;

    public void addFormatter(FormatterRegistry formatterRegistry){
        formatterRegistry.addFormatter(categoryEnumFormatter);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/login").setViewName("login");
    }


}
