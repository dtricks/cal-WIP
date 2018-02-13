package com.example.reset.food_database.objects;

/**
 * Created by Oliver Gras
 */

//class of settings to create settings objects
public class Settings {

    private int id;
    private int maxkcal;

    //getter & setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaxkcal() {
        return maxkcal;
    }

    public void setMaxkcal(int maxkcal) {
        this.maxkcal = maxkcal;
    }

    //constructor
    public Settings(int id, int maxkcal) {
        this.id = id;
        this.maxkcal = maxkcal;
    }

    //to String
    @Override
    public String toString() {
        return "Settings{" +
                "id=" + id +
                ", maxkcal=" + maxkcal +
                '}';
    }
}
