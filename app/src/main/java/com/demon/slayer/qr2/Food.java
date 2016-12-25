package com.demon.slayer.qr2;

/**
 * Created by SUJOY GHOSH on 08-12-2016.
 */
public class Food {
int id;
    String food;
    String cost;
int qtyprice;

    public Food() {
    }

    public Food(int id, String food, String cost) {
        this.id = id;
        this.food = food;
        this.cost = cost;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public int getId() {

        return id;
    }

    public String getFood() {

        return food;
    }

    public int getQtyprice() {
        return qtyprice;
    }

    public void setQtyprice(int qtyprice) {
        this.qtyprice = qtyprice;
    }

    public String getCost() {
        return cost;
    }

    public Food(String food, String cost, int qtyprice) {

        this.food = food;
        this.cost = cost;
    this.qtyprice=qtyprice;
    }
}
