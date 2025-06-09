package com.study.shop.view;

import com.study.shop.dao.AdminDao;
import com.study.shop.dao.ShopownerDao;
import com.study.shop.po.Admin;
import com.study.shop.po.Shopowner;

import java.util.Scanner;

public class ShopownerView {
    // 创建dao
    private static ShopownerDao shopownerDao = new ShopownerDao();
    private static Scanner sc = new Scanner(System.in);
    private static AdminDao adminDao = new AdminDao();
    private static Scanner scanner = new Scanner(System.in);
//    public static void main(String[] args) {
//        showLoginMenu();
//    }

    /**
     * 显示登录菜单
     */
//    public static void showLoginMenu() {
//        System.out.println("--------------欢迎进入店主系统--------------");
//        while (true) {
//            System.out.println("1. 店主登录");
//            System.out.println("2. 管理员登录");
//            System.out.println("3. 返回主菜单");
//            System.out.print("请输入你的选择：");
//
//            int choice = scanner.nextInt();
//            scanner.nextLine(); // 消耗换行符
//
//            switch (choice) {
//                case 1:
//                    shopownerLogin();
//                    break;
//                case 2:
//                    adminLogin();
//                    break;
//                case 3:
//                    return;
//                default:
//                    System.out.println("无效的选择，请重新输入！");
//            }
//        }
//    }



    /**
     * 店主登录
     */
    public static boolean shopownerLogin() {
        System.out.println("--------------欢迎进入店主登录界面--------------");
        while (true) {
            System.out.println("请输入手机号：");
            String phone = sc.next();
            System.out.println("请输入密码：");
            String password = sc.next();
            // 调用dao
            boolean result = shopownerDao.login(phone, password);
            if (result) {
                mainMenu();
            } else {
                System.out.println("手机号或密码错误，请重新输入");
            }
        }
    }




    public static void mainMenu() {
        while (true) {
            System.out.println("-------------------------------------");
            System.out.println("\t1. 类目管理");
            System.out.println("\t2. 餐品管理");
            System.out.println("\t3. 订单管理");
            System.out.println("\t4. 统计管理");
            System.out.println("\t5. 查看评价");
            System.out.println("\t0. 退出系统");
            System.out.println("-------------------------------------");
            System.out.println("请选择业务：");
            String option = sc.next();
            switch (option) {
                case "1":
                    CategoryManager.categoryMenu();
                    break;
                case "2":
                    FoodManager.foodMenu();
                    break;
                case "3":
                    OrderManager.orderMenu();
                    break;
                case "4":
                    CountManager.countMenu();
                    break;
                case "5":
                    AppraiseManager.appraiseMenu();
                    break;
                case "0":
                    System.exit(0);
                default:
                    System.out.println("没有该选项，请重新输入！");
            }
        }
    }
}