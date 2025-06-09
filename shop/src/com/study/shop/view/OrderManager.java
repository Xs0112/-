package com.study.shop.view;

import com.study.shop.dao.OrderDao;
import com.study.shop.po.Order;

import java.util.List;
import java.util.Scanner;

public class OrderManager {
    public static Scanner sc = new Scanner(System.in);
    // 引入Dao
    public static OrderDao orderDao = new OrderDao();
    public static void orderMenu(){
        System.out.println("------------订单管理------------");
        while (true){
            //循环打印未处理订单
            List<Order> list = orderDao.findNoHandleRecord();
            for (Order order : list) {
                System.out.println(order);
            }
            System.out.println("1.全部接单  2.部分接单  3.取消订单 4.返回上一步 0.退出系统");
            String option = sc.next();
            switch(option){
                case "1":
                    reciveAll();
                    break;
                case "2":
                    recivePart();
                    break;
                case "3":
                    cancleOrder();
                case "4":
                    ShopownerView.mainMenu();
                case "0":
                    System.exit(0);
                default:
                    System.out.println("无效选择，请重新输入！");
            }
        }

    }

    // 全部接单
    private static void reciveAll() {
        int result = orderDao.receiveAll();
        if(result>0){
            System.out.println("完成接单");
        }else{
            System.out.println("接单失败/当前无订单");
        }
    }


    // 部分接单
    private static void recivePart() {
        System.out.println("输入要接单的编号（中间用逗号间隔）：");
        String oids = sc.next();
        int result = orderDao.receivePart(oids);
        if(result>0){
            System.out.println("完成接单");
        }else{
            System.out.println("接单失败");
        }
    }


    private static void cancleOrder() {
        System.out.println("输入要删除的编号（中间用都好间隔）：");
        String oids = sc.next();
        int result = orderDao.cancleOrder(oids);
        if(result>0){
            System.out.println("取消完成");
        }else{
            System.out.println("取消失败");
        }
    }
}
