package com.example.reset.food_database.foodlist_recipe;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.reset.food_database.DatabaseHandler;
import com.example.reset.food_database.objects.Food;
import android.app.AlertDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oliver Gras
 */

//logic for adding foods from the list of foods to a recipe
public class logic {

    private Activity activity;
    private database data;
    private gui gui;
    private List<Food> foodList = new ArrayList<Food>();
    List<String> stringList = new ArrayList<String>();

    public logic(Activity act, database data, gui gui) {
        super();
        this.data = data;
        this.gui = gui;
        activity = act;


        fillList();

        gui.getFoodListRecipe().setTextFilterEnabled(true);
        setupSearchView();
    }

    //creates counter for Searchview to get the right id while using the filter function
    public void foodlistRecipeItemClicked(String selectedItem){


        int counter = 0;

        for(int i=0; i < stringList.size(); i++)
        {
            if(stringList.get(i).equals(selectedItem))
            {
                counter = i;
            }
        }

        final int selectedItemId = counter;

        //opens dialog window for adding chosen food to a list with a quantity
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Please choose the number of portions you want to add to your recipe?");
        //builder.setIcon(R.drawable.icon);

        builder.setPositiveButton("Add to Diary",
                new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {

                        int foodID = foodList.get(selectedItemId).getId();

                        Food food = foodList.get(selectedItemId);

                        Toast.makeText(activity, food.toString(), Toast.LENGTH_LONG).show();

                    }
                });


        builder.create().show();

    }

    //sets up the search
    private void setupSearchView() {
        gui.getFoodSearch().setIconifiedByDefault(false);
        gui.getFoodSearch().setSubmitButtonEnabled(true);
        gui.getFoodSearch().setQueryHint("Search Here");

    }

    //fills list with foods from the database
    private void fillList() {

        DatabaseHandler db = new DatabaseHandler(activity);
        foodList = db.getFoodList();
        List<String> foodAdapter = new ArrayList<String>();

        for (Food object: foodList) {
            double quantity = object.getQuantity();
            String quantityBearbeitet =  String.format(((quantity % 1.0D) == 0.0D) ? "%.0f" : "%.1f", quantity);
            String unitName = object.getUnit().getName();
            String foodName = object.getName();
            String kcal = Integer.toString(object.getKcal());
            foodAdapter.add(quantityBearbeitet + " " + unitName + " " + foodName + " (" + kcal + " kcal)");
            stringList.add(quantityBearbeitet + " " + unitName + " " + foodName + " (" + kcal + " kcal)");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity, android.R.layout.simple_list_item_1, foodAdapter);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        gui.getFoodListRecipe().setAdapter(adapter);
    }
}
