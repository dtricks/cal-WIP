package com.example.reset.food_database.objects;

import com.example.reset.food_database.DatabaseHandler;
import com.example.reset.food_database.add_unit.init;

/**
 * Created by Oliver Gras
 */

//class of food to create food objects
public class Food {

    private String name;
    private int kcal;
    private double quantity;
    private Unit unit;
    private int id;

    //constructor
    public Food(String name, int kcal, double quantity, Unit unit, int id) {
        this.name = name;
        this.kcal = kcal;
        this.quantity = quantity;
        this.unit = unit;
        this.id = id;
    }

    //to String
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

    //constructor
    public Food(){
    }

    //constructor
    public Food(String name, int kcal, double quantity, Unit unit) {
        this.name = name;
        this.kcal = kcal;
        this.quantity = quantity;
        this.unit = unit;
    }

    //getter & setter
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
