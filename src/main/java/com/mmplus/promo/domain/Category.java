package com.mmplus.promo.domain;

import com.mmplus.promo.utils.Constants;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum Category {
    ALCOHOL("ALCOHOL"){
        public String toString(){
            return Constants.ALCOHOL;
        }
    },

    BEVERAGES("BEVERAGES"){
        public String toString(){
            return Constants.BEVERAGES;
        }
    },

    GASTRONOMY("GASTRONOMY"){
        @Override
        public String toString() {
            return Constants.GASTRONOMY;
        }
    },

    CONFECTIONERY("CONFECTIONERY"){
        @Override
        public String toString() {
            return Constants.CONFECTIONERY;
        }
    },

    GROCERY("GROCERY"){
        @Override
        public String toString() {
            return Constants.GROCERY;
        }
    },

    HOUSEHOLD("HOUSEHOLD"){
        @Override
        public String toString() {
            return Constants.HOUSEHOLD;
        }
    };

    private String categoryValue;

    private static final Map<String, Category> map = Arrays.stream(Category.values())
            .collect(Collectors.toMap(category ->category.categoryValue, category->category));

    Category(String categoryValue){
        this.categoryValue = categoryValue;
    }

    public Category getCategoryValue(String categoryValue){
        return map.get(categoryValue);
    }

    public String getCategoryValue(){
        return categoryValue;
    }

}
