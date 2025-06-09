package com.study.shop.view;

import com.study.shop.dao.AppraiseDao;
import com.study.shop.dao.CategoryDao;
import com.study.shop.dao.FoodDao;
import com.study.shop.dao.OrderDao;
import com.study.shop.po.Appraise;
import com.study.shop.po.Food;
import com.study.shop.po.Order;

import java.util.*;

public class CustomerManager {
    public static Scanner sc = new Scanner(System.in);

    // 引入Dao
    public static OrderDao orderDao = new OrderDao();
    public static FoodDao foodDao = new FoodDao();
    // 引入评价Dao
    public static AppraiseDao appraiseDao = new AppraiseDao();

    // 顾客主界面
    public static void customerMenu() {
        System.out.println("-----------顾客界面----------");
        while (true) {
            System.out.println("\t1. 点餐");
            System.out.println("\t2. 点餐记录查询");
            System.out.println("\t3. 评价");
            System.out.println("\t0. 退出系统");
            System.out.println("请选择业务");
            String option = sc.next();
            switch (option) {
                case "1":
                    orderFood();
                    break;
                case "2":
                    findRecord();
                    break;
                case "3":
                    evaluateFood();
                    break;
                case "0":
                    System.exit(0);
                default:
                    System.out.println("您的选择有误，请重新选择！");
            }
        }
    }

    // 辅助方法1：打印餐品
    private static void printFoodByCategory(List<Food> list) {
        // 创建一个比较器，根据餐品的类型名称比较
        Comparator<Food> comparator = new Comparator<Food>(){
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
            for (int i = 0; i < appraiseList.size(); i++) {
                Appraise appraise = appraiseList.get(i);
                System.out.print("评价" + (i + 1) + "：" + appraise.getAppraise() + " ");
                System.out.println("星级：" + appraise.getScore());
            }
        }

    }

    /**
     * 辅助方法2：选择餐品加入购物车
     *
     * @param list 所有餐品
     * @return 购物车集合 cartList
     */
    private static List<Order> addCart(List<Food> list) {
        List<Order> cartList = new ArrayList<>();
        while (true) {
            System.out.println("餐品编号：");
            int foodId = sc.nextInt();
            System.out.println("手机号：");
            String phone = sc.next();
            System.out.println("数量：");
            int amount = sc.nextInt();
            // 辅助方法2.1：获得商品价格
            int price = getFoodPrice(foodId, list);
            // 计算总价
            int total = amount * price;
            // 选择堂食或外卖
            System.out.println("请选择订单类型：0.堂食 1.外卖");
            int orderType = sc.nextInt();
            String address = "";
            int deskNumber = 0;
            if (orderType == 1) {
                System.out.println("请输入送餐地址：");
                address = sc.next();
            }else {
                address = "堂食";
                System.out.println("桌号：");
                 deskNumber = sc.nextInt();
            }
            String foodName = foodDao.findById(foodId).getFoodName();
            // 组合对象
            Order order = new Order(deskNumber, phone, address, foodId,foodName ,price, amount, total, orderType);

            // 添加到购物车
            cartList.add(order);
            System.out.println("是否继续：1是 0否");
            int select = sc.nextInt();
            if (select == 0) {
                break;
            }
        }
        return cartList;
    }

    /**
     * 辅助方法2.2：获得餐品价格
     *
     * @param foodId
     * @param list
     * @return
     */
    private static int getFoodPrice(int foodId, List<Food> list) {
        int price = 0;
        for (Food food : list) {
            if (food.getFoodId() == foodId) {
                price = food.getPrice();
            }
        }
        return price;
    }

    /**
     * 辅助方法3：管理购物车
     *
     * @param cartList
     * @return
     */
    private static List<Order> manageCartList(List<Order> cartList) {
        System.out.println("-------------购物车------------");

        while (true) {
            if(cartList.isEmpty()){
                System.out.println("购物车为空");
                customerMenu();
            }
            for (Order order : cartList) {
//                System.out.println(order);
                System.out.println("FoodName: "+order.getFoodName() + "  单价" + order.getPrice() + "  数量：" + order.getAmount() + "  总价" + order.getTotal());
            }
            System.out.println("1.删除  2.更改数量  3.确定");
            int option = sc.nextInt();
            if (option == 1) {
                System.out.println("要删除的餐品编号：");
                int foodId = sc.nextInt();
                // 辅助方法3.1 从购物车删除餐品
                deleteCart(foodId, cartList);
            } else if (option == 2) {
                System.out.println("更改数量的餐品编号：");
                int foodId = sc.nextInt();
                System.out.println("数量：");
                int count = sc.nextInt();
                // 辅助方法3.2 更改购物车中餐品数量
                updateCart(foodId, count, cartList);
            } else if (option == 3) {
                break;
            }
        }
        return cartList;
    }

    /**
     * 辅助方法3.1 删除购物车餐品
     *
     * @param foodId
     * @param cartList
     */
    private static void deleteCart(int foodId, List<Order> cartList) {
        for (Order order : cartList) {
            if (order.getFoodId() == foodId) {
                cartList.remove(order);
                break;
            }
        }
    }

    /**
     * 辅助方法3.2 修改购物车餐品数量
     *
     * @param foodId
     * @param count
     * @param cartList
     */
    private static void updateCart(int foodId, int count, List<Order> cartList) {
        for (Order order : cartList) {
            if (order.getFoodId() == foodId) {
                // 重新设置数量
                order.setAmount(count);
            }
        }
    }

    /**
     * 辅助方法4：获得订单总金额
     *
     * @param orderList 订单集合，
     * @return 总金额
     */
    private static int getOrderMoney(List<Order> orderList) {
        int money = 0;
        for (Order order : orderList) {
            if (order.getOrderType() == 1) {
                money += 2; // 如果是外卖，加两元打包费
            }
            money += order.getTotal();
        }
        return money;
    }

    // 点餐
    private static void orderFood() {
        System.out.println("---------------  点餐  ------------");
        // 打印餐品信息
        List<Food> list = foodDao.findAll();
        // 辅助方法1：根据餐品类目排序
        printFoodByCategory(list);

        System.out.println("1.点餐  2.退出");
        int option1 = sc.nextInt();
        if (option1 == 2) {
            customerMenu(); // 回到顾客页面
        }
        // 点餐
        // 辅助方法2：加入购物车
        List<Order> cartList = addCart(list);
        // 辅助方法3：管理购物车-得到最终订单
        List<Order> orderList = manageCartList(cartList);
        // 打印订单
        for (Order order : orderList) {
            System.out.println(order);
        }
        System.out.println("是否提交订单：1是 0 否");
        int option2 = sc.nextInt();
        if (option2 == 1) {
            // 辅助方法4：获得总金额
            int money = getOrderMoney(orderList);
            System.out.println("总金额：" + money);
            while (true) {
                // 提交订单
                System.out.println("支付金额：");
                int cash = sc.nextInt();
                // 辅助方法4：判断金额是否和订单金额一致
                if (cash == money) {
                    boolean result = orderDao.addOrder(orderList);
                    if (result == true) {
                        System.out.println("下单成功");
                    } else {
                        System.out.println("下单失败");
                    }
                    break;
                } else {
                    System.out.println("金额错误，请重新输入！");
                }

            }
        }
    }

    // 查询记录
    private static void findRecord() {
        System.out.println("--------- 订单查询-----------");
        System.out.println("手机号：");
        String phone = sc.next();
        System.out.println("1.当天未处理订单  2.当天已处理订单 3.所有订单");
        int option = sc.nextInt();
        List<Order> list = orderDao.findRecord(phone, option);
        for (Order order : list) {
            System.out.println(order);
        }
    }

    // 评价功能
    private static void evaluateFood() {
        System.out.println("----------- 评价餐品 -----------");
        System.out.println("请输入手机号：");
        String phone = sc.next();
        List<Order> list = orderDao.findRecord(phone, 2);
        if(list.isEmpty())
        {
            System.out.println("您还没有点过餐或商家未接单，请您先点餐或等待接单后再进行评价");
            return;
        }
        System.out.println("-------本店所有菜品如下：--------");
        List<Food> foodList = foodDao.findAll();
        for (Food food : foodList) {
            System.out.println(food.getFoodId() + ". " + food.getFoodName());
        }
        // 过滤重复的FoodName
        List<Order> filteredList = new ArrayList<>();
        Map<String, Boolean> seenFoodNames = new HashMap<>();

        for (Order order : list) {
            if (!seenFoodNames.containsKey(order.getFoodName())) {
                filteredList.add(order);
                seenFoodNames.put(order.getFoodName(), true);
            }
        }
        System.out.println("您已点过的菜品如下：");

        // 输出过滤后的列表
        for (Order order : filteredList) {
            System.out.println("FoodId：" + order.getFoodId() + " FoodName：" + order.getFoodName() + "  价格：" + order.getPrice() + "  数量：" + order.getAmount());
        }

        System.out.println("请选择要评价的餐品编号：");
        int foodId = sc.nextInt();
        boolean flag = false;
        while(!flag){
            for(Order order : list){
                if(order.getFoodId() == foodId){
                    flag = true;
                }
            }
            if(!flag){
                System.out.println("您还没有点过这道菜,请重新输入您要评价的餐品");
                foodId = sc.nextInt();
            }
        }
        System.out.println("请输入星级（1 - 5星）：");
        int star = sc.nextInt();
        while (star < 1 || star > 5) {
            System.out.println("星级必须在1 - 5之间，请重新输入：");
            star = sc.nextInt();
        }
        System.out.println("请输入评价内容：");
        String comment = sc.next();

        // 创建评价对象并设置属性
        Appraise appraise = new Appraise();
        appraise.setFood_id(foodId);
        String foodname="";
        for(Order food : list){
            if(food.getFoodId() == foodId){
                foodname = food.getFoodName();
                break;
            }
        }
        appraise.setFood_name(foodname);
        appraise.setAppraise(comment);
        appraise.setScore(star);

        // 调用AppraiseDao的add方法将评价信息存入数据库
        int result = appraiseDao.add(appraise);
        if (result > 0) {
            System.out.println("评价成功！");
        } else {
            System.out.println("评价失败！");
        }
    }
}