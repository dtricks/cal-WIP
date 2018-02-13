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
    private double portion;
    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }
    private Date date;
    private Unit unit;
    private double quantity;

    //getter & setter
    public double getQuantity() {
        return quantity;
    }

    public double setQuantity(double quantity) {
        return this.quantity = quantity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
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

    public double getPortion() {
        return portion;
    }

    public void setPortion(double portion) {
        this.portion = portion;
    }


    public DiaryEntry() {
    }

    //constructor
    public DiaryEntry(int id, String foodname, int kcal, int portion, String unit) {
        this.id = id;
        this.foodname = foodname;
        this.kcal = kcal;
        this.portion = portion;
        this.unit = new Unit(unit);
    }

    public DiaryEntry(int id, String foodname, int kcal, int portion, Unit unit) {
        this.id = id;
        this.foodname = foodname;
        this.kcal = kcal;
        this.portion = portion;
        this.unit = unit;
    }

    public DiaryEntry(int id, String foodname, int kcal, double portion, Date date, Unit unit, double quantity) {
        this.id = id;
        this.foodname = foodname;
        this.kcal = kcal;
        this.portion = portion;
        this.date = date;
        this.unit = unit;
        this.quantity = quantity;
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




