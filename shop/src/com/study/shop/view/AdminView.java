package com.study.shop.view;

import com.study.shop.dao.AdminDao;
import com.study.shop.po.Admin;

import java.util.List;
import java.util.Scanner;

public class AdminView {
    public static Scanner sc = new Scanner(System.in);
    public static AdminDao adminDao = new AdminDao();
    private static Scanner scanner = new Scanner(System.in);
    /**
     * 管理员登录
     */
    public static void adminLogin() {
        System.out.println("--------------欢迎进入管理员登录界面--------------");
        while (true) {
            System.out.print("请输入用户名：");
            String username = scanner.nextLine();
            System.out.print("请输入密码：");
            String password = scanner.nextLine();

            Admin admin = adminDao.login(username, password);
            if(admin!=null){
                adminMenu(admin);
            }
            else {
                System.out.println("登录失败，用户名或密码错误！");
            }
        }
    }
    public static void adminMenu() {
        while (true) {
            System.out.println("------欢迎进入管理员账号管理界面------");
            System.out.println("\t1. 增加管理员账号");
            System.out.println("\t2. 查询管理员账号");
            System.out.println("\t3. 修改管理员账号");
            System.out.println("\t4. 删除管理员账号");
            System.out.println("\t5. 返回上一菜单");
            System.out.println("\t0. 退出系统");
            System.out.println("------------------------------");
            System.out.println("请选择业务：");
            String option = sc.next();
            switch (option) {
                case "1":
                    addAdmin();
                    break;
                case "2":
                    queryAdmin();
                    break;
                case "3":
                    updateAdmin();
                    break;
                case "4":
                    deleteAdmin();
                    break;
                case "5":
//                    adminMenu();
                    // 返回上一菜单的逻辑
                    return;
                case "0":
                    System.exit(0);
                default:
                    System.out.println("无效选择，请重新输入！");
            }
        }
    }
    //管理员界面
    public static void adminMenu(Admin admin) {
        if (admin != null) {
            System.out.println("登录成功，欢迎管理员 " + admin.getUsername() + "！");
//                ShopownerAccountManager.accountMenu();
            //选择店主账号管理还是管理员账号管理
            while (true) {
                System.out.println("1. 店主账号管理");
                System.out.println("2. 管理员账号管理");
                int select = scanner.nextInt();
                switch (select) {
                    case 1:
                        ShopownerAccountManager.accountMenu();
                        break;
                    case 2:
                        AdminView.adminMenu();
                        break;
                    default:
                        System.out.println("输入错误，请重新选择！");
                }
            }
        } else {
            System.out.println("登录失败，用户名或密码错误！");
        }
    }
    // 增加管理员账号
    private static void addAdmin() {
        System.out.println("--------------- 增加管理员账号 -------------");
        System.out.println("用户名：");
        String username = sc.next();
        System.out.println("密码：");
        String password = sc.next();
        System.out.println("手机号：");
        String phone = sc.next();
//        System.out.println("创建时间：");
//        String createTime = sc.next();

        // 组合对象
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(password);
        admin.setPhone(phone);
//        admin.setCreateTime(createTime);

        // 调用Dao
        int result = adminDao.add(admin);
        if (result > 0) {
            System.out.println("增加管理员账号成功！");
        } else {
            System.out.println("增加管理员账号失败！");
        }
    }

    // 查询管理员账号
    private static void queryAdmin() {
        System.out.println("------------- 查询管理员账号 -----------");
        // 显示所有管理员账号
        List<Admin> list = adminDao.findAll();
        for (Admin admin : list) {
            System.out.println(admin);
        }
    }

    // 修改管理员账号
    private static void updateAdmin() {
        System.out.println("--------------  修改管理员账号 --------------");
        // 打印所有管理员账号信息
        List<Admin> list = adminDao.findAll();
        for (Admin admin : list) {
            System.out.println(admin);
        }
        System.out.println("选择要修改的管理员账号编号：");
        int id = sc.nextInt();
        Admin admin = adminDao.findById(id);
        if (admin == null) {
            System.out.println("没有该管理员账号信息");
            return;
        }
        System.out.println(admin);
        System.out.println("重新输入管理员账号信息：");
        System.out.println("用户名：");
        String username = sc.next();
        admin.setUsername(username);
        System.out.println("密码：");
        String password = sc.next();
        admin.setPassword(password);
        System.out.println("手机号：");
        String phone = sc.next();
        admin.setPhone(phone);
        System.out.println("创建时间：");
        String createTime = sc.next();
        admin.setCreateTime(createTime);

        // 调用Dao
        int result = adminDao.update(admin);
        if (result > 0) {
            System.out.println("修改管理员账号成功！");
        } else {
            System.out.println("修改管理员账号失败！");
        }
    }

    // 删除管理员账号
    private static void deleteAdmin() {
        System.out.println("------------- 删除管理员账号 --------------");
        // 打印所有管理员账号信息
        List<Admin> list = adminDao.findAll();
        for (Admin admin : list) {
            System.out.println(admin);
        }
        System.out.println("选择要删除的管理员账号编号：");
        int id = sc.nextInt();
        Admin admin = adminDao.findById(id);
        if (admin == null) {
            System.out.println("没有该管理员账号信息");
            return;
        }
        System.out.println(admin);
        System.out.println("确定要删除吗？1是 0否");
        int select = sc.nextInt();
        if (select == 1) {
            // 执行删除
            int result = adminDao.delete(id);
            if (result > 0) {
                System.out.println("删除管理员账号成功！");
            } else {
                System.out.println("删除管理员账号失败！");
            }
        }
    }
}