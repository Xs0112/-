package com.study.shop.po;

public class Count {
    public Integer id;
    public Integer food_id;
    public String food_name;
    public int count;
    public int money;
    public String category_name;
    public int category_id;

    public Count(){

    }

    public Count(Integer id, Integer food_id, String food_name, int count, int money, String category_name, int category_id) {
        this.id = id;
        this.food_id = food_id;
        this.food_name = food_name;
        this.count = count;
        this.money = money;
        this.category_name = category_name;
        this.category_id = category_id;
    }

    public Count(Integer food_id, String food_name, int count, int money, String category_name, int category_id) {
        this.food_id = food_id;
        this.food_name = food_name;
        this.count = count;
        this.money = money;
        this.category_name = category_name;
        this.category_id = category_id;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFood_id() {
        return food_id;
    }
    public void setFood_id(Integer food_id) {
        this.food_id = food_id;
    }

    public String getFood_name() {
        return food_name;
    }
    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }

    public int getMoney() {
        return money;
    }
    public void setMoney(int money) {
        this.money = money;
    }
    public String getCategory_name() {
        return category_name;
    }
    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public int getCategory_id() {
        return category_id;
    }
    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }
}
