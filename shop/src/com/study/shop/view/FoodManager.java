package com.study.shop.view;

import com.study.shop.dao.AppraiseDao;
import com.study.shop.dao.CategoryDao;
import com.study.shop.dao.FoodDao;
import com.study.shop.po.Appraise;
import com.study.shop.po.Category;
import com.study.shop.po.Food;
import com.study.shop.util.ValidatorUtil;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FoodManager {
    public static Scanner sc = new Scanner(System.in);
    public static void foodMenu(){
        while(true) {
            System.out.println("-------------餐品管理----------");
            System.out.println("\t1.增加餐品");
            System.out.println("\t2.查询餐品");
            System.out.println("\t3.修改餐品");
            System.out.println("\t4.删除餐品");
            System.out.println("\t5.返回上一菜单");
            System.out.println("\t0.退出系统");
            System.out.println("------------------------------");
            System.out.println("请选择业务：");
            String option = sc.next();
            switch (option) {
                case "1":
                    addFood();
                    break;
                case "2":
                    queryFood();
                    break;
                case "3":
                    updateFood();
                    break;
                case "4":
                    deleteFood();
                    break;
                case "5":
                    ShopownerView.mainMenu();
                case "0":
                    System.exit(0);
            }
        }
    }

    public static CategoryDao categoryDao = new CategoryDao();
    public static FoodDao foodDao = new FoodDao();
    public static AppraiseDao appraiseDao = new AppraiseDao();
    // 增加餐品
    private static void addFood() {
        System.out.println("--------------- 增加餐品 -------------");
        System.out.println("餐品名称：");
        String foodName = sc.next();
        // 验证餐品名称是否为中文汉字且不重复
        while (!ValidatorUtil.isChinese(foodName) || ValidatorUtil.isFoodNameDuplicate(foodName)) {
            if (!ValidatorUtil.isChinese(foodName)) {
                System.out.println("餐品名称必须为中文汉字，请重新输入！");
            } else {
                System.out.println("餐品名称已存在，请重新输入！");
            }
            foodName = sc.next();
        }
        System.out.println("所属类目：");
        System.out.println();
        List<Category> list = categoryDao.findAll();
        // 打印类目列表
        for (Category category : list) {
            System.out.println(category);
        }
        System.out.println("选择所属类目编号：");
        int categoryId = sc.nextInt();
        System.out.println("餐品价格：");
        int price = sc.nextInt();
        System.out.println("餐品描述：");
        String description = sc.next();
        while (!isChinese(description)) {
            System.out.println("餐品描述必须为中文汉字，请重新输入！");
            description = sc.next();
        }
        // 组合对象
        Food food = new Food(foodName,categoryId,price,description);
        // 调用Dao
        int result = foodDao.add(food);
        if(result > 0){
            System.out.println("增加餐品成功！");
        }else{
            System.out.println("增加餐品失败！");
        }

    }


    // 查询餐品
    private static void queryFood() {
        System.out.println("------------- 查询餐品 -----------");
        // 显示所有餐品
        List<Food> allList = foodDao.findAll();
        for ( Food food : allList){
             System.out.println(food);
        }
        System.out.println("是否按关键字查询：1是 0否");
        int select = sc.nextInt();
        if(select == 1){
            System.out.println("请输入查询关键字：");
            String key = sc.next();
//            while (!isChinese(key)) {
//                System.out.println("餐品名称必须为中文汉字，请重新输入！");
//                key = sc.next();
//            }
            List<Food> keyList = foodDao.findByKey(key);
            for (Food food : keyList) {
                System.out.println(food);
                //评价+星级
                List<Appraise> appraiseList = appraiseDao.findByFoodId(food.getFoodId());
                for (Appraise appraise : appraiseList) {
                    System.out.print("评价：" + appraise.getAppraise() + " ");
                    System.out.println("星级：" + appraise.getScore());
                }
            }
        }
    }

    // 修改餐品
    private static void updateFood() {
        System.out.println("--------------  修改餐品 --------------");
        // 打印所有餐品信息
        List<Food> list = foodDao.findAll();
        for (Food food : list) {
            System.out.println(food);
        }
        System.out.println("选择要修改的餐品编号：");
        int foodId = sc.nextInt();
        Food food = foodDao.findById(foodId);
        if(food == null){
            System.out.println("没有该餐品信息");
            return;
        }
        System.out.println(food);
        System.out.println("重新输入餐品信息：");
        System.out.println("餐品名称：");
        String foodName = sc.next();
        while (!isChinese(foodName)) {
            System.out.println("餐品名称必须为中文汉字，请重新输入！");
            foodName = sc.next();
        }
        food.setFoodName(foodName);
        System.out.println("所属类目：");
        List<Category> clist = categoryDao.findAll();
        // 打印类目列表
        for (Category category : clist) {
            System.out.println(category);
        }
        System.out.println("选择所属类目编号：");
        int categoryId = sc.nextInt();
        food.setCategoryId(categoryId);
        System.out.println("餐品价格：");
        int price = sc.nextInt();
        food.setPrice(price);
        System.out.println("餐品描述：");
        String description = sc.next();
        food.setDescription(description);
        // 调用Dao
        int result = foodDao.update(food);
        if(result > 0){
            System.out.println("修改餐品成功！");
        }else{
            System.out.println("修改餐品失败！");
        }
    }

    // 删除餐品
    private static void deleteFood() {
        System.out.println("------------- 删除餐品 --------------");
        // 打印所有餐品信息
        List<Food> list = foodDao.findAll();
        for (Food food : list) {
            System.out.println(food);
        }
        System.out.println("选择要删除的餐品编号：");
        int foodId = sc.nextInt();
        Food food = foodDao.findById(foodId);
        if(food == null){
            System.out.println("没有该餐品信息");
            return;
        }
        System.out.println(food);
        System.out.println("确定要删除吗？1是 0否");
        int select = sc.nextInt();
        if(select == 1){
            // 执行删除
            int result = foodDao.delete(foodId);
            if(result > 0){
                System.out.println("删除餐品成功！");
            }else{
                System.out.println("删除餐品失败！");
            }
        }

    }

    // 用于判断字符串是否全是中文汉字的方法
    private static boolean isChinese(String str) {
        String regEx = "[\u4e00-\u9fa5]+";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    // 辅助方法1：打印餐品
    private static void printFoodByCategory(List<Food> list) {
        // 创建一个比较器，根据餐品的类型名称比较
        Comparator<Food> comparator = new Comparator<Food>() {
            @Override
            public int compare(Food o1, Food o2) {
                return o1.getCategoryName().compareTo(o2.getCategoryName());
            }
        };
        // 根据餐品类型排序
        Collections.sort(list, comparator);
        //打印餐品
        for (Food food : list) {
            System.out.println(food);
            List<Appraise> appraiseList = appraiseDao.findByFoodId(food.getFoodId());
            for (Appraise appraise : appraiseList) {
                System.out.print("评价：" + appraise.getAppraise() + " ");
                System.out.println("星级：" + appraise.getScore());
            }
        }
    }
}
