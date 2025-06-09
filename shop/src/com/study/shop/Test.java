package com.study.shop;

import com.study.shop.view.AdminView;
import com.study.shop.view.CustomerManager;
import com.study.shop.view.ShopownerAccountManager;
import com.study.shop.view.ShopownerView;

import javax.swing.text.View;
import java.util.Scanner;

public class Test {
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        ShopownerView shopownerView = new ShopownerView();
        CustomerManager customerManager = new CustomerManager();
        AdminView admin = new AdminView();
        //选择登录方式
        while(true){
            System.out.println("--------欢迎使用本系统！--------");
            System.out.println("1. 店主登录");
            System.out.println("2. 管理员登录");
            System.out.println("3. 顾客登录");
            System.out.println("4. 退出");
            System.out.print("请输入你的选择：");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    shopownerView.shopownerLogin();
                    break;
                case 2:
                    admin.adminLogin();
                    break;
                case 3:
                    customerManager.customerMenu();
                    break;
                case 4:
                    System.out.println("感谢使用本系统！");
                    System.exit(0);
                default:
                    System.out.println("无效的选择，请重新输入！");
            }

        }
    }
}
