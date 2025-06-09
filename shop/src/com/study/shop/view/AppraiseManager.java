package com.study.shop.view;

import com.study.shop.dao.AppraiseDao;
import com.study.shop.po.Appraise;

import java.util.List;
import java.util.Scanner;

public class AppraiseManager {
    public static Scanner sc = new Scanner(System.in);
    public static AppraiseDao appraiseDao = new AppraiseDao();

    public static void appraiseMenu() {
        while (true) {
            System.out.println("-------------评价管理----------");
            System.out.println("\t1. 查看所有评价");
            System.out.println("\t2. 根据餐品编号查看评价");
            System.out.println("\t3. 返回上一菜单");
            System.out.println("\t0. 退出系统");
            System.out.println("------------------------------");
            System.out.println("请选择业务：");
            String option = sc.next();
            switch (option) {
                case "1":
                    viewAllAppraises();
                    break;
                case "2":
                    viewAppraisesByFoodId();
                    break;
                case "3":
                    ShopownerView.mainMenu();
                    break;
                case "0":
                    System.exit(0);
                default:
                    System.out.println("无效选择，请重新输入！");
            }
        }
    }

    // 查看所有评价
    private static void viewAllAppraises() {
        System.out.println("------------- 查看所有评价 -------------");
        // 这里假设 AppraiseDao 有一个 findAll 方法来查询所有评价
        // 如果没有该方法，可以通过修改 SQL 查询来实现
        List<Appraise> appraiseList = appraiseDao.findAll();
        if (appraiseList.isEmpty()) {
            System.out.println("暂无评价信息。");
        } else {
            for (Appraise appraise : appraiseList) {
                System.out.println(appraise);
            }
        }
    }

    // 根据餐品编号查看评价
    private static void viewAppraisesByFoodId() {
        System.out.println("------------- 根据餐品编号查看评价 -------------");
        System.out.println("请输入餐品编号：");
        int foodId = sc.nextInt();
        List<Appraise> appraiseList = appraiseDao.findByFoodId(foodId);
        if (appraiseList.isEmpty()) {
            System.out.println("该餐品暂无评价信息。");
        } else {
            for (Appraise appraise : appraiseList) {
                System.out.println(appraise);
            }
        }
    }
}