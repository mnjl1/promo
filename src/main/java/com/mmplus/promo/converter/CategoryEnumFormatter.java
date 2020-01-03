package com.mmplus.promo.converter;

import com.mmplus.promo.domain.Category;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;

@Service
public class CategoryEnumFormatter implements Formatter<Optional<Category>> {
    @Override
    public Optional<Category> parse(String s, Locale locale)  {
        return Arrays.stream(Category.values()).filter(category -> category.toString().equals(s)).findAny();
    }

    @Override
    public String print(Optional<Category> category, Locale locale) {
        return category.toString();
    }
}
