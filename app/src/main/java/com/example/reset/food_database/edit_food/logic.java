
package com.example.reset.food_database.edit_food;

import android.app.Activity;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.reset.food_database.DatabaseHandler;
import com.example.reset.food_database.objects.Food;
import com.example.reset.food_database.objects.Unit;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Oliver Gras
 */

//logic for editing foodentries
public class logic {

    private Activity activity;
    private database data;
    private gui gui;
    private List<Unit> unitList = new ArrayList<Unit>();
    private Food currentFood;

    public logic(Activity act, database data, gui gui) {
        super();
        this.data = data;
        this.gui = gui;
        activity = act;
        fillSpinner();

        //getting the Id of the food chosen in list_food
        Intent intent = activity.getIntent();
        if (intent != null) {
            DatabaseHandler db = new DatabaseHandler(activity);
            db.getReadableDatabase();
            currentFood = db.getFood_new(intent.getIntExtra("handoverId", 0));
        }

        gui.getQuantityEditText().setText(String.valueOf(currentFood.getQuantity()));
        gui.getKcalEditText().setText(String.valueOf(currentFood.getKcal()));
        gui.getFoodnameEditText().setText(String.valueOf(currentFood.getName()));

        fillSpinner();

        gui.getUnitEditSpinner().setSelection(getIndex(gui.getUnitEditSpinner(), currentFood.getUnit()));


    }

    //updates the foodentry
    public void submitFoodEditButtonClicked() {
        String foodText = gui.getFoodnameEditText().getText().toString();
        String kcalText = gui.getKcalEditText().getText().toString();
        String quantityText = gui.getQuantityEditText().getText().toString();
        String unitText = gui.getUnitEditSpinner().getSelectedItem().toString();


        if (foodText.isEmpty() || kcalText.isEmpty() || quantityText.isEmpty()) {
            Toast.makeText(activity, "Please fill in the whole formular!", Toast.LENGTH_SHORT).show();
        } else {
            DatabaseHandler db = new DatabaseHandler(activity);

            db.updateFood(currentFood.getId(),foodText, Integer.parseInt(kcalText), Double.parseDouble(quantityText), unitText);

            Toast.makeText(activity, foodText + " has been edited!", Toast.LENGTH_SHORT).show();
            Intent myIntent = new Intent(activity, com.example.reset.food_database.list_food.init.class);
            activity.startActivity(myIntent);
        }
    }
    //filling the unit spinner
    private void fillSpinner() {

        DatabaseHandler db = new DatabaseHandler(activity);
        db.getReadableDatabase();

        unitList = db.getUnits_new();

        List<String> unitAdapter = new ArrayList<String>();

        //unitAdapter = db.getUnits();
        for (Unit object : unitList) {
            unitAdapter.add(object.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, unitAdapter);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gui.getUnitEditSpinner().setAdapter(adapter);
    }

    //getting id & name of the unit
    private int getIndex(Spinner spinner, String unitname) {
        int index = 0;

        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).equals(unitname)) {
                index = i;
                break;
            }
        }
        return index;
    }

}


