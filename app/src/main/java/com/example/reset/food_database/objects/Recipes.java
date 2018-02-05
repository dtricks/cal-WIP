package com.example.reset.food_database.objects;

/**
 * Created by C4RL0zZ0 on 05.02.2018.
 */

public class Recipes {

    private int id;
    private String name;
    private int kcal;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Recipes(int id, String name, int kcal) {
        this.id = id;
        this.name = name;
        this.kcal = kcal;
    }

    @Override
    public String toString() {
        return "Recipes{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", kcal=" + kcal +
                '}';
    }
}
