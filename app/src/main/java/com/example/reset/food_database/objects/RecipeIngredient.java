package com.example.reset.food_database.objects;

/**
 * Created by Oliver Gras
 */

//class of recipeingredients to create recipeingredients objects
public class RecipeIngredient {

    private int id;
    private int recipeId;
    private String foodname;
    private String unit;
    private double quantity;
    private int kcal;
    private double portion;

    //constructor
    public RecipeIngredient() {
    }

    public RecipeIngredient(int id, int recipeId, String foodname, double quantity, int kcal, String unit, double portion) {
        this.id = id;
        this.recipeId = recipeId;
        this.foodname = foodname;
        this.quantity = quantity;
        this.kcal = kcal;
        this.unit = unit;
        this.portion = portion;
    }

    //getter & setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String food) {
        this.foodname = food;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public int getKcal() {
        return kcal;
    }

    public void setKcal(int kcal) {
        this.kcal = kcal;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getPortion() {
        return portion;
    }

    public void setPortion(double portion) {
        this.portion = portion;
    }

    //to String

    @Override
    public String toString() {
        return "RecipeIngredient{" +
                "id=" + id +
                ", recipeId=" + recipeId +
                ", foodname='" + foodname + '\'' +
                ", unit='" + unit + '\'' +
                ", quantity=" + quantity +
                ", kcal=" + kcal +
                ", portion=" + portion +
                '}';
    }


}