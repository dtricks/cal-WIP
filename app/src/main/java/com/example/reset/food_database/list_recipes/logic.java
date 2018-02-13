package com.example.reset.food_database.list_recipes;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;
import android.widget.Toast;

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
    private com.example.reset.food_database.list_recipes.gui gui;
    private List<Recipes> recipesList = new ArrayList<Recipes>();
    List<String> stringList = new ArrayList<String>();

    public logic(Activity act, database data, com.example.reset.food_database.list_recipes.gui gui) {
        super();
        this.data = data;
        this.gui = gui;
        activity = act;


        fillList();

        gui.getRecipeList().setTextFilterEnabled(true);
        setupSearchView();
    }

    //leads user to add_Recipe
    public void addRecipeButtonClicked(){
        Intent myIntent = new Intent(activity, com.example.reset.food_database.recipe_addtolist.init.class);
        activity.startActivity(myIntent);
    }

    //creates counter for Searchview to get the right id while using the filter function
    public void itemClicked(String selectedItem){

        int counter = 0;

        for(int i=0; i < stringList.size(); i++)
        {
            if(stringList.get(i).equals(selectedItem))
            {
                counter = i;
            }
        }

        final int selectedItemId = counter;

        //opens Dialog with the 3 choices edit food, delete food, add food to diary
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("What would You like to do with your choosen food?");

        //leads user to addtodiary
        builder.setPositiveButton("Add to Diary",
                new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {

                        int recipeID = recipesList.get(selectedItemId).getId();

                        Recipes recipes = recipesList.get(selectedItemId);

                        Toast.makeText(activity, recipes.toString(), Toast.LENGTH_LONG).show();

                    }
                });

        //leads user to editRecipe + handover the chosen recipes id
        builder.setNeutralButton("Edit",
                new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        DatabaseHandler db = new DatabaseHandler(activity);
                        db.calculateRecipeKcal(recipesList.get(selectedItemId).getId());
                        Intent alertIntent = new Intent(activity, com.example.reset.food_database.list_ingredients.init.class);
                        alertIntent.putExtra("handoverId", recipesList.get(selectedItemId).getId());
                        activity.startActivity(alertIntent);
                    }
                });

        //deletes the clicked Recipe
        builder.setNegativeButton("Delete",
                new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        int recipeID = recipesList.get(selectedItemId).getId();


                        DatabaseHandler db = new DatabaseHandler(activity);

                        if(db.deleteRecipe(recipeID)){
                            Toast.makeText(activity, recipesList.get(selectedItemId).getName() + " has been successfully deleted!", Toast.LENGTH_SHORT).show();
                            fillList();
                        }
                        else{
                            Toast.makeText(activity, "Deleting was NOT successful!", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
        builder.create().show();

    }

    //sets up the search
    private void setupSearchView() {
        gui.getRecipeSearch().setIconifiedByDefault(false);
        gui.getRecipeSearch().setSubmitButtonEnabled(true);
        gui.getRecipeSearch().setQueryHint("Search Here");

    }

    //filling the list with foods from database
    private void fillList() {

        DatabaseHandler db = new DatabaseHandler(activity);
        recipesList = db.getRecipeList();
        List<String> foodAdapter = new ArrayList<String>();

        for (Recipes object: recipesList) {
            double quantity = object.getKcal();
            String quantityBearbeitet =  String.format(((quantity % 1.0D) == 0.0D) ? "%.0f" : "%.1f", quantity);
          // String unitName = object.getUnit().getName();
            String recipeName = object.getName();
            String kcal = Integer.toString(object.getKcal());
           foodAdapter.add(recipeName + " (" + kcal + " kcal)");
            stringList.add(recipeName +  " (" + kcal + " kcal)");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity, android.R.layout.simple_list_item_1, foodAdapter);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        gui.getRecipeList().setAdapter(adapter);
    }


}
