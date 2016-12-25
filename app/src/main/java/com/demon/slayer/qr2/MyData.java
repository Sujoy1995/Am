package com.demon.slayer.qr2;

/**
 * Created by SUJOY GHOSH on 07-12-2016.
 */
public class MyData {
    String foodname;
    String cost;
    String description;
    String food_id;

    public MyData(String foodname, String cost, String description) {
        this.foodname = foodname;
        this.cost = cost;
        this.description = description;
    }

    public MyData(String foodname, String cost, String description, String food_id) {
        this.foodname = foodname;
        this.cost = cost;
        this.description = description;
        this.food_id = food_id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFood_id() {
        return food_id;
    }

    public void setFood_id(String food_id) {

        this.food_id = food_id;
    }

    public String getDescription() {

        return description;
    }

    public MyData(String foodname, String cost) {
        this.foodname = foodname;
        this.cost = cost;
    }

    public String getFoodname() {
        return foodname;
    }

    public String getCost() {
        return cost;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}
