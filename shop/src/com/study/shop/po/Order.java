package com.study.shop.po;

import java.util.Date;

public class Order {
    private Integer orderId;
    private Integer deskNumber;
    private String phone;
    private String address; // 新增字段，客户地址
    private Integer foodId;
    private String foodName; // 显示数据用
    private Integer price; // 冗余字段，方便查询统计
    private Integer amount;
    private Integer total;
    private Date createTime;
    private int state;
    private int orderType; // 0表示堂食，1表示外卖

    public Order() {
    }

    public Order(Integer orderId, Integer deskNumber, String phone, String address, Integer foodId, String foodName, Integer price, Integer amount, Integer total, Date createTime, int state, int orderType) {
        this.orderId = orderId;
        this.deskNumber = deskNumber;
        this.phone = phone;
        this.address = address;
        this.foodId = foodId;
        this.foodName = foodName;
        this.price = price;
        this.amount = amount;
        this.total = total;
        this.createTime = createTime;
        this.state = state;
        this.orderType = orderType;
    }

    public Order(Integer deskNumber, String phone, String address, Integer foodId, String foodName, Integer price, Integer amount, Integer total, Date createTime, int state, int orderType) {
        this.deskNumber = deskNumber;
        this.phone = phone;
        this.address = address;
        this.foodId = foodId;
        this.foodName = foodName;
        this.price = price;
        this.amount = amount;
        this.total = total;
        this.createTime = createTime;
        this.state = state;
        this.orderType = orderType;
    }

    public Order(int deskNumber, String phone, String address, int foodId, int price, int amount, int total, int orderType) {
        this.deskNumber = deskNumber;
        this.phone = phone;
        this.address = address;
        this.foodId = foodId;
        this.price = price;
        this.amount = amount;
        this.total = total;
        this.orderType = orderType;
    }

    public Order(int deskNumber, String phone, String address, int foodId, Object o, int price, int amount, int total, int orderType) {
        this.deskNumber = deskNumber;
        this.phone = phone;
        this.address = address;
        this.foodId = foodId;
        this.price = price;
        this.amount = amount;
        this.total = total;
        this.orderType = orderType;
    }
    public  Order(int deskNumber,String phone, String address,int  foodId,String foodName ,int price,int amount,int total,int orderType){
        this.deskNumber = deskNumber;
        this.phone = phone;
        this.address = address;
        this.foodId = foodId;
        this.foodName = foodName;
        this.price = price;
        this.amount = amount;
        this.total = total;
        this.orderType = orderType;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getDeskNumber() {
        return deskNumber;
    }

    public void setDeskNumber(Integer deskNumber) {
        this.deskNumber = deskNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", deskNumber=" + deskNumber +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", foodId=" + foodId +
                ", foodName='" + foodName + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                ", total=" + total +
                ", createTime=" + createTime +
                ", state=" + state +
                ", orderType=" + orderType +
                '}';
    }
}