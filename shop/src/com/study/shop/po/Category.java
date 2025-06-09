package com.study.shop.po;

public class Category {
    private Integer categoryId;
    private String categoryName;
    private boolean deleted;

    public Category() {

    }

    public Category(String categoryName, boolean deleted) {
        this.categoryName = categoryName;
        this.deleted = deleted;
    }

    public Category(Integer categoryId, String categoryName, boolean deleted) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.deleted = deleted;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    public String getCategoryName() {
        return categoryName;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
    public boolean isDeleted() {
        return deleted;
    }

    @Override
    public String toString() {
        return "Category{" + "categoryId=" + categoryId +
                "，categoryName='" + categoryName + '\'' +
                "， deleted=" + deleted +
                '}';
    }

}
