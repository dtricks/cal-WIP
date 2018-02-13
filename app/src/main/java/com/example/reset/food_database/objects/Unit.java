package com.example.reset.food_database.objects;

/**
 * Created by Oliver Gras
 */

//class of unit to create unit objects
public class Unit {

    private String name;
    private int id;

    //getter & setter
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

    //constructor
    public Unit(int id, String name){
        this.id=id;
        this.name=name;
    }

    public Unit(String name){
        this.name=name;
    }

    public Unit(){
    }

    //to String
    @Override
    public String toString() {
        return "Unit{" +
                "name='" + name + '\'' +
                '}';
    }
}
