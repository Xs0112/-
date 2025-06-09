package com.study.shop.view;

import com.study.shop.dao.ShopownerAccountDao;
import com.study.shop.po.Shopowner;
import org.w3c.dom.ls.LSOutput;

import java.util.List;
import java.util.Scanner;

public class ShopownerAccountManager {
    public static Scanner sc = new Scanner(System.in);
    public static ShopownerAccountDao shopownerAccountDao = new ShopownerAccountDao();

    public static void accountMenu() {
        while (true) {
            System.out.println("------欢迎进入店主账号管理界面------");
            System.out.println("\t1. 增加店主账号");
            System.out.println("\t2. 查询店主账号");
            System.out.println("\t3. 修改店主账号");
            System.out.println("\t4. 删除店主账号");
            System.out.println("\t5. 返回上一菜单");
            System.out.println("\t0. 退出系统");
            System.out.println("------------------------------");
            System.out.println("请选择业务：");
            String option = sc.next();
            switch (option) {
                case "1":
                    addShopowner();
                    break;
                case "2":
                    queryShopowner();
                    break;
                case "3":
                    updateShopowner();
                    break;
                case "4":
                    deleteShopowner();
                    break;
                case "5":

                    return;
                case "0":
                    System.exit(0);
                default:
                    System.out.println("无效选择，请重新输入！");
            }
        }
    }

    // 增加店主账号
    private static void addShopowner() {
        System.out.println("--------------- 增加店主账号 -------------");
        System.out.println("店铺地址：");
        String address = sc.next();
        System.out.println("店主姓名：");
        String shopownerName = sc.next();
        System.out.println("手机号：");
        String phone = sc.next();
        System.out.println("密码：");
        String password = sc.next();

        // 组合对象
        Shopowner shopowner = new Shopowner(address, shopownerName, phone, password);
        // 调用Dao
        int result = shopownerAccountDao.add(shopowner);
        if (result > 0) {
            System.out.println("增加店主账号成功！");
        } else {
            System.out.println("增加店主账号失败！");
        }
    }

    // 查询店主账号
    private static void queryShopowner() {
        System.out.println("------------- 查询店主账号 -----------");
        // 显示所有店主账号
        List<Shopowner> list = shopownerAccountDao.findAll();
        for (Shopowner shopowner : list) {
            System.out.println(shopowner);
        }
    }

    // 修改店主账号
    private static void updateShopowner() {
        System.out.println("--------------  修改店主账号 --------------");
        // 打印所有店主账号信息
        List<Shopowner> list = shopownerAccountDao.findAll();
        for (Shopowner shopowner : list) {
            System.out.println(shopowner);
        }
        System.out.println("选择要修改的店主账号编号：");
        int shopId = sc.nextInt();
        Shopowner shopowner = shopownerAccountDao.findById(shopId);
        if (shopowner == null) {
            System.out.println("没有该店主账号信息");
            return;
        }
        System.out.println(shopowner);
        System.out.println("重新输入店主账号信息：");
        System.out.println("店铺地址：");
        String address = sc.next();
        shopowner.setAddress(address);
        System.out.println("店主姓名：");
        String shopownerName = sc.next();
        shopowner.setShopowner(shopownerName);
        System.out.println("手机号：");
        String phone = sc.next();
        shopowner.setPhone(phone);
        System.out.println("密码：");
        String password = sc.next();
        shopowner.setPassword(password);

        // 调用Dao
        int result = shopownerAccountDao.update(shopowner);
        if (result > 0) {
            System.out.println("修改店主账号成功！");
        } else {
            System.out.println("修改店主账号失败！");
        }
    }

    // 删除店主账号
    private static void deleteShopowner() {
        System.out.println("------------- 删除店主账号 --------------");
        // 打印所有店主账号信息
        List<Shopowner> list = shopownerAccountDao.findAll();
        for (Shopowner shopowner : list) {
            System.out.println(shopowner);
        }
        System.out.println("选择要删除的店主账号编号：");
        int shopId = sc.nextInt();
        Shopowner shopowner = shopownerAccountDao.findById(shopId);
        if (shopowner == null) {
            System.out.println("没有该店主账号信息");
            return;
        }
        System.out.println(shopowner);
        System.out.println("确定要删除吗？1是 0否");
        int select = sc.nextInt();
        if (select == 1) {
            // 执行删除
            int result = shopownerAccountDao.delete(shopId);
            if (result > 0) {
                System.out.println("删除店主账号成功！");
            } else {
                System.out.println("删除店主账号失败！");
            }
        }
    }
}