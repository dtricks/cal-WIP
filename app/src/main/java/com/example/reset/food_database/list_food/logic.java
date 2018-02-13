
package com.example.reset.food_database.list_food;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.reset.food_database.DatabaseHandler;
import com.example.reset.food_database.objects.Food;
import android.app.AlertDialog;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Oliver Gras
 */

//logic for the list of foods
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

        gui.getFoodList().setTextFilterEnabled(true);
        setupSearchView();
    }

    //leads user to add_food
    public void addFoodButtonClicked(){
        Intent myIntent = new Intent(activity, com.example.reset.food_database.add_food.init.class);
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

                        int foodID = foodList.get(selectedItemId).getId();

                        Intent alertIntent = new Intent(activity, com.example.reset.food_database.addtodiary.init.class);
                        alertIntent.putExtra("handoverId", foodList.get(selectedItemId).getId());
                        activity.startActivity(alertIntent);

                        //for testing purposes TODO remove
                        Toast.makeText(activity, foodList.get(selectedItemId).toString(), Toast.LENGTH_LONG).show();

                    }
                });

        //leads user to editfood + handover the chosen foods id
        builder.setNeutralButton("Edit",
                new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        Intent alertIntent = new Intent(activity, com.example.reset.food_database.edit_food.init.class);
                        alertIntent.putExtra("handoverId", foodList.get(selectedItemId).getId());
                        activity.startActivity(alertIntent);
                    }
                });

        //deletes the clicked food
        builder.setNegativeButton("Delete",
                new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        int foodID = foodList.get(selectedItemId).getId();


                        DatabaseHandler db = new DatabaseHandler(activity);

                        if(db.deleteFood(foodID)){
                            Toast.makeText(activity, foodList.get(selectedItemId).getName() + " has been successfully deleted!", Toast.LENGTH_SHORT).show();
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
        gui.getFoodSearch().setIconifiedByDefault(false);
        gui.getFoodSearch().setSubmitButtonEnabled(true);
        gui.getFoodSearch().setQueryHint("Search Here");

    }

    //filling the list with foods from database
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
        gui.getFoodList().setAdapter(adapter);
    }
}