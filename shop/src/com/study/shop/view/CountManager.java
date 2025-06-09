package com.study.shop.view;

import com.study.shop.dao.CountDao;
import com.study.shop.po.Count;
import java.util.List;
import java.util.Scanner;

public class CountManager {
    private static Scanner sc = new Scanner(System.in);
    private static CountDao countDao = new CountDao();

    // 统计管理菜单展示
    public static void countMenu() {
        while (true) {
            System.out.println("-------------统计管理----------------");
            System.out.println("\t1. 按销量统计（食品）");
            System.out.println("\t2. 按销售额统计（食品）");
            System.out.println("\t3. 按销量统计（类目）");
            System.out.println("\t4. 按销售额统计（类目）");
            System.out.println("\t5. 返回上一菜单");
            System.out.println("\t0. 退出系统");
            System.out.println("-------------------------------------");
            System.out.println("请选择业务：");
            String option = sc.next();
            switch (option) {
                case "1":
                    countByQuantity();
                    break;
                case "2":
                    countBySales();
                    break;
                case "3":
                    countQuantityByCategory();
                    break;
                case "4":
                    countSalesByCategory();
                    break;
                case "5":
                    ShopownerView.mainMenu();
                case "0":
                    System.exit(0);
                    break;
                default:
                    System.out.println("无效选择，请重新输入！");
            }
        }
    }

    // 按销量统计（食品）并展示结果
    private static void countByQuantity() {
        System.out.println("------------- 按销量统计（食品） -------------");
        List<Count> countList = countDao.findCountByQuantity();
        printCountFood(countList);
    }

    // 按销售额统计（食品）并展示结果
    private static void countBySales() {
        System.out.println("------------- 按销售额统计（食品） -------------");
        List<Count> countList = countDao.findCountBySales();
        printpriceFood(countList);
    }

    // 按销量统计（类目）并展示结果
    private static void countQuantityByCategory() {
        System.out.println("------------- 按销量统计（类目） -------------");
        List<Count> countList = countDao.countQuantityByCategory();
        printCountCategory(countList);
    }

    // 按销售额统计（类目）并展示结果
    private static void countSalesByCategory() {
        System.out.println("------------- 按销售额统计（类目） -------------");
        List<Count> countList = countDao.countSalesByCategory();
        printpriceCategory(countList);
    }

    // 销量（食品）
    private static void printCountFood(List<Count> list) {
        System.out.println("销量从高到低排序为：");
        for (Count count : list) {
            System.out.println(
                    "食品名称: " + count.getFood_name() +
                    ", 销量: " + count.getCount() +
                    ", 销售额: " + count.getMoney());
        }
    }

    //销售额（食品）
    private static void printpriceFood(List<Count> list) {
        System.out.println("销售额从高到低排序为：");
        for (Count count : list) {
            System.out.println(
                    "食品名称: " + count.getFood_name() +
                            ", 销量: " + count.getCount() +
                            ", 销售额: " + count.getMoney());
        }
    }

    // 销量（类目）
    private static void printCountCategory(List<Count> list) {
        System.out.println("销量从高到低排序为：");
        for (Count count : list) {
            System.out.println(
                    "类目名称: " + count.getCategory_name() +
                            ", 销量: " + count.getCount() +
                            ", 销售额: " + count.getMoney());
        }
    }

    //销售额（类目）
    private static void printpriceCategory(List<Count> list) {
        System.out.println("销售额从高到低排序为：");
        for (Count count : list) {
            System.out.println(
                    "食品名称: " + count.getCategory_name() +
                            ", 销量: " + count.getCount() +
                            ", 销售额: " + count.getMoney());
        }
    }

}