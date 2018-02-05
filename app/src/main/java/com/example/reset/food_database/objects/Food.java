package com.example.reset.food_database.objects;

import com.example.reset.food_database.DatabaseHandler;
import com.example.reset.food_database.add_unit.activity_add_unit;

/**
 * Created by C4RL0zZ0 on 05.02.2018.
 */

public class Food {

    private String name;
    private int kcal;
    private double quantity;
    private Unit unit;
    private int id;

    public Food(String name, int kcal, double quantity, Unit unit, int id) {
        this.name = name;
        this.kcal = kcal;
        this.quantity = quantity;
        this.unit = unit;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {

        return "Food{" +
                "id=" + id +
                " ,name='" + name + '\'' +
                ", kcal=" + kcal +
                ", quantity=" + quantity +
                ", unit=" + unit.getId() +
                '}';
    }

    public Food(){
    }

    public Food(String name, int kcal, double quantity, Unit unit) {
        this.name = name;
        this.kcal = kcal;
        this.quantity = quantity;
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKcal() {
        return kcal;
    }

    public void setKcal(int kcal) {
        this.kcal = kcal;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
