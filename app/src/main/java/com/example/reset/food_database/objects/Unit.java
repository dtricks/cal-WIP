package com.example.reset.food_database.objects;

/**
 * Created by C4RL0zZ0 on 05.02.2018.
 */

public class Unit {

    private String name;
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Unit(int id, String name){
        this.id=id;
        this.name=name;
    }
    public Unit(){
    }

    @Override
    public String toString() {
        return "Unit{" +
                "name='" + name + '\'' +
                '}';
    }
}
