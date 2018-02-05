package com.example.reset.food_database.add_food;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.reset.food_database.DatabaseHandler;
import com.example.reset.food_database.list_food.list_food;
import com.example.reset.food_database.R;
import com.example.reset.food_database.add_unit.activity_add_unit;
import com.example.reset.food_database.objects.Food;
import com.example.reset.food_database.objects.Unit;

import java.util.ArrayList;
import java.util.List;

public class activity_add_food extends AppCompatActivity {

    EditText foodName;
    EditText kcal;
    EditText quantity;
    Spinner unitListSpinner;

    Button submitFood;
    ImageButton addUnitButton;
    ImageButton deleteUnitButton;

    List<Unit> unitList = new ArrayList<Unit>();

    //comment2asdfugasd
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_food);

        foodName = (EditText) findViewById(R.id.name_textfield);
        kcal = (EditText) findViewById(R.id.kcal_textfield);
        quantity = (EditText) findViewById(R.id.quantity_textfield);
        unitListSpinner = (Spinner) findViewById(R.id.unit_spinner);
        submitFood = (Button)findViewById(R.id.submitfoodbutton);
        addUnitButton = (ImageButton)findViewById(R.id.addunit);
        deleteUnitButton = (ImageButton)findViewById(R.id.deleteunit);

        fillSpinner(unitListSpinner);

        addUnitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), activity_add_unit.class);
                startActivity(intent);
            }

        });


        deleteUnitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String currentUnit = unitListSpinner.getSelectedItem().toString();
                if(currentUnit.equals("g") ||currentUnit.equals("EL") ||currentUnit.equals("TL") || currentUnit.equals("Stueck") || currentUnit.equals("ml") || currentUnit.equals("Portion")) {
                    Toast.makeText(getApplicationContext(), "Die Einheiten g, EL, TL, Stueck, ml und Portion können nicht gelöscht werden!", Toast.LENGTH_SHORT).show();
                }
                else {
                    DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                    if (db.deleteUnit(currentUnit) == true) {
                        Toast.makeText(getApplicationContext(), currentUnit + " erfolgreich entfernt!", Toast.LENGTH_SHORT).show();
                        fillSpinner(unitListSpinner);
                    }
                }
            }

        });

        submitFood.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String foodText = foodName.getText().toString();
                String kcalText = kcal.getText().toString();
                String quantityText = quantity.getText().toString();
               // String unitText = unitListSpinner.getSelectedItem().toString();

                int unitText = unitList.get(unitListSpinner.getSelectedItemPosition()).getId();

                if (foodText.isEmpty() || kcalText.isEmpty() || quantityText.isEmpty() ) {
                    Toast.makeText(getApplicationContext(), "Bitte das Formular ausfüllen!", Toast.LENGTH_SHORT).show();
                }
                else {
                    DatabaseHandler db = new DatabaseHandler(getApplicationContext());

                    db.insertFood(foodText, Integer.parseInt(kcalText), Integer.parseInt(quantityText), unitText);

                    Toast.makeText(getApplicationContext(), foodText + " wurde hinzugefügt!", Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(view.getContext(), list_food.class);
                    startActivity(myIntent);
                }
            }}
        );

    }

    private void fillSpinner(Spinner spinner) {

        DatabaseHandler db = new DatabaseHandler(this);
        db.getReadableDatabase();

        unitList = db.getUnits_new();

        List<String> unitAdapter = new ArrayList<String>();

        //unitAdapter = db.getUnits();
        for (Unit object: unitList) {
            unitAdapter.add(object.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, unitAdapter);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    }

