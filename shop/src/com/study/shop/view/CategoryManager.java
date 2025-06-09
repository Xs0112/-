package com.study.shop.view;

import com.study.shop.dao.CategoryDao;
import com.study.shop.po.Category;
import com.study.shop.util.ValidatorUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CategoryManager {
    public static CategoryDao categoryDao = new CategoryDao();
    public static Scanner sc = new Scanner(System.in);

    public static void categoryMenu() {
        while (true) {
            System.out.println("-------------类目管理----------");
            System.out.println("\t1.增加类目");
            System.out.println("\t2.查询类目");
            System.out.println("\t3.修改类目");
            System.out.println("\t4.删除类目");
            System.out.println("\t5.返回上一菜单");
            System.out.println("\t0.退出系统");
            System.out.println("------------------------------");
            System.out.println("请选择业务：");
            String option = sc.next();
            switch (option) {
                case "1":
                    addCategory();
                    break;
                case "2":
                    queryCategory();
                    break;
                case "3":
                    updateCategory();
                    break;
                case "4":
                    deleteCategory();
                    break;
                case "5":
                    ShopownerView.mainMenu();
                case "0":
                    System.exit(0);
                default:
                    System.out.println("无效选择，请重新输入！");
            }
        }

    }

    private static void addCategory() {
        System.out.println("------------- 增加类目 -------------");
        System.out.println("类目名称：");// 类目编号自增
        String categoryName = sc.next();
        // 验证类目名称是否为中文汉字
        while (!ValidatorUtil.isChinese(categoryName) || ValidatorUtil.isCategoryNameDuplicate(categoryName)) {
            if (!ValidatorUtil.isChinese(categoryName)) {
                System.out.println("类目名称必须为中文汉字，请重新输入！");
            } else {
                System.out.println("类目名称已存在，请重新输入！");
            }
            categoryName = sc.next();
        }

        // 封装对象
        Category category = new Category();
        category.setCategoryName(categoryName);
        // 调用dao
        int result = categoryDao.add(category);
        if (result > 0) {
            System.out.println("增加类目成功！");
        } else {
            System.out.println("增加类目失败！");
        }
    }

    private static void queryCategory() {
        System.out.println("----------  查询类目 --------");
        // 打印所有类目信息
        List<Category> list = categoryDao.findAll();
        printCategoryList(list);
        System.out.println("是否选择关键字查询：1是 0否");
        int select = sc.nextInt();
        if (select == 1) {
            System.out.println("请输入查询关键字：");
            String key = sc.next();
            // 验证查询关键字是否为中文汉字
            while (!ValidatorUtil.isChinese(key)) {
                System.out.println("查询关键字必须为中文汉字，请重新输入！");
                key = sc.next();
            }
            List<Category> keyList = categoryDao.findByKey(key);
            if (keyList.isEmpty()) {
                System.out.println("没有关键字为" + key + "的类目");
            } else {
                printCategoryList(keyList);
            }
        }
    }

    // 打印类目集合信息
    private static void printCategoryList(List<Category> list) {
        for (Category category : list) {
            System.out.println(category);
        }
    }

    private static void updateCategory() {
        System.out.println("--------------  修改类目 --------------");
        // 打印所有类目信息
        List<Category> list = categoryDao.findAll();
        printCategoryList(list);
        System.out.println("选择要修改的类目编号：");
        int categoryId = sc.nextInt();
        Category category = categoryDao.findById(categoryId);
        if (category == null) {
            System.out.println("没有该类目信息");
            return;
        }
        System.out.println(category);
        System.out.println("重新输入类目信息：");
        System.out.println("类目名称:");
        String categoryName = sc.next();
        //  数据验证
        while (!ValidatorUtil.isChinese(categoryName) || ValidatorUtil.isCategoryNameDuplicate(categoryName)) {
            if (!ValidatorUtil.isChinese(categoryName)) {
                System.out.println("类目名称必须为中文汉字，请重新输入！");
            } else {
                System.out.println("类目名称已存在，请重新输入！");
            }
            categoryName = sc.next();
        }

        // 重新设置属性
        category.setCategoryName(categoryName);
        // 调用dao
        int result = categoryDao.update(category);
        if (result > 0) {
            System.out.println("修改类目成功！");
        } else {
            System.out.println("修改类目失败！");
        }
    }

    private static void deleteCategory() {
        System.out.println("------------- 删除类目 --------------");
        // 打印所有类目信息
        List<Category> list = categoryDao.findAll();
        printCategoryList(list);
        System.out.println("选择要删除的类目编号：");
        int categoryId = sc.nextInt();
        Category category = categoryDao.findById(categoryId);
        if (category == null) {
            System.out.println("没有该类目信息");
            return;
        }
        System.out.println(category);
        System.out.println("确定要删除吗？1是 0否");
        int select = sc.nextInt();
        if (select == 1) {
            // 执行删除
            int result = categoryDao.delete(categoryId);
            if (result > 0) {
                System.out.println("删除类目成功！");
            } else {
                System.out.println("删除类目失败！");
            }
        }
    }


}