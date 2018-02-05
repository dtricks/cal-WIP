package com.example.reset.food_database.objects;

/**
 * Created by C4RL0zZ0 on 05.02.2018.
 */

public class Unit {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Unit(String name){
        this.name=name;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "name='" + name + '\'' +
                '}';
    }
}
