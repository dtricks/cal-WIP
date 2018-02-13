package com.example.reset.food_database.objects;

/**
 * Created by Oliver Gras
 */

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

//class of diaryentry to create diaryentry objects
public class DiaryEntry {

    private int id;
    private String foodname;
    private int kcal;
    private int portion;
    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        return dateFormat.format(date);
    }
    private String unit;

    //getter & setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public int getKcal() {
        return kcal;
    }

    public void setKcal(int kcal) {
        this.kcal = kcal;
    }

    public int getPortion() {
        return portion;
    }

    public void setPortion(int portion) {
        this.portion = portion;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    //constructor
    public DiaryEntry(int id, String foodname, int kcal, int portion, String unit) {
        this.id = id;
        this.foodname = foodname;
        this.kcal = kcal;
        this.portion = portion;
        this.unit = unit;
    }

    //to String
    @Override
    public String toString() {
        return "DiaryEntry{" +
                "id=" + id +
                ", foodname='" + foodname + '\'' +
                ", kcal=" + kcal +
                ", portion=" + portion +
                ", unit='" + unit + '\'' +
                '}';
    }
}




