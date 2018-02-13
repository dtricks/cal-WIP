package com.example.reset.food_database.add_food;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.reset.food_database.DatabaseHandler;
import com.example.reset.food_database.R;
import com.example.reset.food_database.list_food.list_food;
import com.example.reset.food_database.objects.Unit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oliver Gras
 */

//logic for adding food to foodlist
public class logic {

    private Activity activity;
    private database data;
    private gui gui;
    private List<Unit> unitList = new ArrayList <Unit>();

    public logic(Activity act, database data, gui gui) {
        super();
        this.data = data;
        this.gui = gui;
        activity = act;
        fillSpinner();

        //saving the allready written data while creating or editing a unit
        Intent intent = activity.getIntent();
        if (intent != null) {
            gui.getFoodnameText().setText(intent.getStringExtra("name"));
            gui.getQuantityText().setText(intent.getStringExtra("quantity"));
            gui.getKcalText().setText(intent.getStringExtra("kcal"));
            gui.getUnitSpinner().setSelection(getIndex(intent.getStringExtra("unitname")));
        }

    }
        //starts edit_unit
        public void editUnitButtonClicked() {

     //       final int selectedUnitItemId = counter;

            String currentUnit = gui.getUnitSpinner().getSelectedItem().toString();
            if(currentUnit.equals("g") ||currentUnit.equals("EL") ||currentUnit.equals("TL") || currentUnit.equals("Stueck") || currentUnit.equals("ml") || currentUnit.equals("Portion")) {
                Toast.makeText(activity, "Units g, EL, TL, Stueck, ml & Portion can not be edited!", Toast.LENGTH_SHORT).show();
            }
            else {
                Intent intent = new Intent(activity, com.example.reset.food_database.edit_unit.init.class);
                intent.putExtra("name", gui.getFoodnameText().getText().toString());
                intent.putExtra("quantity", gui.getQuantityText().getText().toString());
                intent.putExtra("kcal", gui.getKcalText().getText().toString());
                Intent alertIntent = new Intent(activity, com.example.reset.food_database.edit_food.init.class);
                //alertIntent.putExtra("handoverId", unitList.get(selectedItemId).getUnitId());
                activity.startActivity(alertIntent);
                activity.startActivity(intent);
            }
    }

        //starts add unit
        public void addUnitButtonClicked() {
                Intent intent = new Intent(activity, com.example.reset.food_database.add_unit.init.class);
                intent.putExtra("name", gui.getFoodnameText().getText().toString());
                intent.putExtra("quantity", gui.getQuantityText().getText().toString());
                intent.putExtra("kcal", gui.getKcalText().getText().toString());

                activity.startActivity(intent);
            }

        //deletes the choosen unit from unitlist/spinner
        public void deleteUnitButtonClicked() {

                String currentUnit = gui.getUnitSpinner().getSelectedItem().toString();
                if(currentUnit.equals("g") ||currentUnit.equals("EL") ||currentUnit.equals("TL") || currentUnit.equals("Stueck") || currentUnit.equals("ml") || currentUnit.equals("Portion")) {
                    Toast.makeText(activity, "Units g, EL, TL, Stueck, ml & Portion can not be deleted!", Toast.LENGTH_SHORT).show();
                }
                else {
                    DatabaseHandler db = new DatabaseHandler(activity);
                    if (db.deleteUnit(currentUnit) == true) {
                        Toast.makeText(activity, currentUnit + " has been successfully deleted!", Toast.LENGTH_SHORT).show();
                        fillSpinner();
                    }
                }
            }

        //saving food to footlist
        public void submitFoodButtonClicked() {
                String foodText = gui.getFoodnameText().getText().toString();
                String kcalText = gui.getKcalText().getText().toString();
                String quantityText = gui.getQuantityText().getText().toString();
                int unitText = unitList.get(gui.getUnitSpinner().getSelectedItemPosition()).getId();

                if (foodText.isEmpty() || kcalText.isEmpty() || quantityText.isEmpty() ) {
                    Toast.makeText(activity, "Please fill in the formular!", Toast.LENGTH_SHORT).show();
                }
                else {
                    DatabaseHandler db = new DatabaseHandler(activity);

                    db.insertFood(foodText, Integer.parseInt(kcalText), Double.parseDouble(quantityText), unitText);

                    Toast.makeText(activity, foodText + " has been added!", Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(activity, com.example.reset.food_database.list_food.init.class);
                    activity.startActivity(myIntent);
                }
            }

        //fills the unit spinner
        private void fillSpinner() {

            DatabaseHandler db = new DatabaseHandler(activity);
            db.getReadableDatabase();

        unitList = db.getUnits_new();

        List<String> unitAdapter = new ArrayList<String>();

        for (Unit object: unitList) {
            unitAdapter.add(object.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, unitAdapter);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gui.getUnitSpinner().setAdapter(adapter);
    }

    //getting the id of the current unit
    private int getIndex(String unitname) {
        int index = 0;

        for (int i = 0; i < gui.getUnitSpinner().getCount(); i++) {
            if (gui.getUnitSpinner().getItemAtPosition(i).equals(unitname)) {
                index = i;
                break;
            }
        }
        return index;
    }
}
