package com.mmplus.promo.utils;

import com.mmplus.promo.domain.Category;

import java.util.HashMap;

public class UploadFileHelper {

    public static Category getCategory(String categoryNumber){
        HashMap<String, Category> categories = new HashMap<>();
        categories.put("230902", Category.CONFECTIONERY);
        return categories.get(categoryNumber);
    }


}
