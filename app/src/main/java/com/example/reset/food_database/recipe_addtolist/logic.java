package com.example.reset.food_database.recipe_addtolist;


import android.app.Activity;
import android.content.Intent;

import com.example.reset.food_database.DatabaseHandler;
import com.example.reset.food_database.objects.Recipes;

import java.util.ArrayList;
import java.util.List;

//import com.example.reset.food_database.objects.Food;


/**
 * Created by Oliver Gras
 */

//logic for the list of recipes
public class logic {

    private Activity activity;
    private database data;
    private com.example.reset.food_database.recipe_addtolist.gui gui;
    private List<Recipes> recipesList = new ArrayList<Recipes>();
    List<String> stringList = new ArrayList<String>();

    public logic(Activity act, database data, com.example.reset.food_database.recipe_addtolist.gui gui) {
        super();
        this.data = data;
        this.gui = gui;
        activity = act;

    }

    //leads user to add_Recipe
    public void createRecipeButtonClicked(){
        String recipeName = gui.getRecipename().getText().toString();
        int kcal = 0;

        DatabaseHandler db = new DatabaseHandler(activity);

        db.insertRecipe(recipeName, kcal ); //Integer.parseInt("3") );

        Intent myIntent = new Intent(activity, com.example.reset.food_database.list_recipes.init.class);
        activity.startActivity(myIntent);
    }






}
