package com.example.reset.food_database.objects;

/**
 * Created by Oliver Gras
 */

//class of recipeingredients to create recipeingredients objects
public class RecipeIngredient {

    private int id;
    private int recipeId;
    private String food;
    private double quantity;
    private int kcal;

    //constructor
    public RecipeIngredient() {
    }

    public RecipeIngredient(int id, int recipeId, String food, double quantity, int kcal) {
        this.id = id;
        this.recipeId = recipeId;
        this.food = food;
        this.quantity = quantity;
        this.kcal = kcal;
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

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
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

    //to String

    @Override
    public String toString() {
        return "RecipeIngredient{" +
                "id=" + id +
                ", recipeId=" + recipeId +
                ", food='" + food + '\'' +
                ", quantity=" + quantity +
                ", kcal=" + kcal +
                '}';
    }
}
