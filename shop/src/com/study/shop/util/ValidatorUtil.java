package com.study.shop.util;

import com.study.shop.dao.CategoryDao;
import com.study.shop.dao.FoodDao;
import com.study.shop.po.Category;
import com.study.shop.po.Food;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorUtil {

    private static CategoryDao categoryDao = new CategoryDao();
    private static FoodDao foodDao = new FoodDao();

    // 用于判断字符串是否全是中文汉字的方法
    public static boolean isChinese(String str) {
        String regex = "[\u4e00-\u9fa5]+";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    // 验证类目名称是否重复
    public static boolean isCategoryNameDuplicate(String categoryName) {
        List<Category> categories = categoryDao.findAll();
        for (Category category : categories) {
            if (category.getCategoryName().equals(categoryName)) {
                return true;
            }
        }
        return false;
    }

    // 验证餐品名称是否重复
    public static boolean isFoodNameDuplicate(String foodName) {
        List<Food> foods = foodDao.findAll();
        for (Food food : foods) {
            if (food.getFoodName().equals(foodName)) {
                return true;
            }
        }
        return false;
    }
}