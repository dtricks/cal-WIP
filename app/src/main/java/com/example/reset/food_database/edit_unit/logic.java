package com.example.reset.food_database.edit_unit;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.example.reset.food_database.DatabaseHandler;

/**
 * Created by Annabella Peekhaus
 */

//logic for editing a unit
public class logic {
    private Activity activity;
    private database data;
    private gui gui;

    public logic(Activity act, database data, gui gui) {
        super();
        this.data = data;
        this.gui = gui;
        activity = act;

        Intent intent = activity.getIntent();




    }

    //upgrades the unit which was chosen for editing
    public void submitEditUnitButtonClicked() {

        String indicatedUnit =  gui.getUnitText().getText().toString();
        DatabaseHandler db = new DatabaseHandler(activity);

        if (indicatedUnit.matches("")){
            Toast.makeText(activity, "Please insert a Unit!", Toast.LENGTH_SHORT).show();

        }
        else if (db.doesUnitExist(indicatedUnit) == true){
            Toast.makeText(activity, "Unit is already existing!", Toast.LENGTH_SHORT).show();

        }
        else {
            db.insertUnit(db.getWritableDatabase(), indicatedUnit);

            Toast.makeText(activity, "Unit " + indicatedUnit + " has been edited!", Toast.LENGTH_SHORT).show();
            Intent intent = activity.getIntent();
            Intent myIntent = new Intent(activity, com.example.reset.food_database.add_food.init.class);
            if (intent != null) {
                myIntent.putExtra("name", intent.getStringExtra("name"));
                myIntent.putExtra("quantity", intent.getStringExtra("quantity"));
                myIntent.putExtra("kcal", intent.getStringExtra("kcal"));
                myIntent.putExtra("unitname", indicatedUnit);
            }
            activity.startActivity(myIntent);
        }
    }



}
