package com.study.shop.po;

public class Shopowner {
    private Integer shopId;
    private String address;
    private String shopowner;
    private String phone;
    private String password;

    public Shopowner() {

    }

    public Shopowner(String address, String shopowner, String phone, String password) {
        this.address = address;
        this.shopowner = shopowner;
        this.phone = phone;
        this.password = password;
    }

    public Shopowner(Integer shopId, String address, String shopowner, String phone, String password) {
        this.shopId = shopId;
        this.address = address;
        this.shopowner = shopowner;
        this.phone = phone;
        this.password = password;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }
    public Integer getShopId() {
        return shopId;
    }

    public void setShopowner(String shopowner) {
        this.shopowner = shopowner;
    }
    public String getShopowner() {
        return shopowner;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getAddress() {
        return address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getPhone() {
        return phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Shopowner{" + "shopId=" + shopId +
                ", address='" + address + '\'' +
                ", shopowner='" + shopowner + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}