package com.study.shop.po;

public class Appraise {
    private int id;
    private String appraise;
    private String food_name;
    private int score;
    private int food_id;

    public Appraise() {}

    public Appraise(int id, String appraise, String food_name, int score, int food_id) {
        this.id = id;
        this.appraise = appraise;
        this.food_name = food_name;
        this.score = score;
        this.food_id = food_id;
    }

    public Appraise(String appraise, String food_name, int price, int food_id) {
        this.appraise = appraise;
        this.food_name = food_name;
        this.score = price;
        this.food_id = food_id;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getAppraise() {
        return appraise;
    }
    public void setAppraise(String appraise) {
        this.appraise = appraise;
    }

    public String getFood_name() {
        return food_name;
    }
    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public int getScore() {
        return score;
    }
    public void setScore(int Score) {
        this.score = Score;
    }

    public int getFood_id() {
        return food_id;
    }
    public void setFood_id(int food_id) {
        this.food_id = food_id;
    }

    @Override
    public String toString() {
        return "Appraise{" +
                "id=" + id +
                ", appraise='" + appraise + '\'' +
                ", food_name='" + food_name + '\'' +
                ", score=" + score +
                ", food_id=" + food_id +
                '}';
    }
}
