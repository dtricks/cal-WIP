package com.example.reset.food_database.objects;

/**
 * Created by C4RL0zZ0 on 05.02.2018.
 */

public class Settings {

    private int id;
    private int maxkcal;

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

    public Settings(int id, int maxkcal) {
        this.id = id;
        this.maxkcal = maxkcal;
    }

    @Override
    public String toString() {
        return "Settings{" +
                "id=" + id +
                ", maxkcal=" + maxkcal +
                '}';
    }
}
