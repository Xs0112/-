package com.study.shop.po;

public class Food {
    private Integer foodId;
    private String foodName;
    private Integer categoryId; // 更新数据用
    private String categoryName; // 显示数据用
    private Integer price;
    private String description;
    private boolean deleted;

    public Food(String foodName, Integer categoryId, String categoryName, Integer price, String description, boolean deleted) {
        this.foodName = foodName;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.price = price;
        this.description = description;
        this.deleted = deleted;
    }

    public Food(Integer foodId, String foodName, Integer categoryId, String categoryName, Integer price, String description, boolean deleted) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.price = price;
        this.description = description;
        this.deleted = deleted;
    }

    public Food() {
    }

    public Food(String foodName, int categoryId, int price, String description) {
        this.foodName = foodName;
        this.categoryId = categoryId;
        this.price = price;
        this.description = description;
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

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }


    @Override
    public String toString() {
        return "Food{" +
                "foodId=" + foodId +
                ", foodName='" + foodName + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", deleted=" + deleted +
                '}';
    }
}