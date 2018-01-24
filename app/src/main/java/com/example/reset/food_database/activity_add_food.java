package com.example.reset.food_database;

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

import java.util.ArrayList;
import java.util.List;

public class activity_add_food extends AppCompatActivity {

    EditText foodName;
    EditText kcal;
    EditText quantity;
    Spinner unitList;

    Button submitFood;
    ImageButton addUnitButton;
    ImageButton deleteUnitButton;
        //comment2asdfugasd
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_food);

        foodName = (EditText) findViewById(R.id.name_textfield);
        kcal = (EditText) findViewById(R.id.kcal_textfield);
        quantity = (EditText) findViewById(R.id.quantity_textfield);
        unitList = (Spinner) findViewById(R.id.unit_spinner);
        submitFood = (Button)findViewById(R.id.submitfoodbutton);
        addUnitButton = (ImageButton)findViewById(R.id.addunit);
        deleteUnitButton = (ImageButton)findViewById(R.id.deleteunit);

        fillSpinner(unitList);

        addUnitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), activity_add_unit.class);
                startActivity(intent);
            }

        });


        deleteUnitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String currentUnit = unitList.getSelectedItem().toString();
                if(currentUnit.equals("g") ||currentUnit.equals("EL") ||currentUnit.equals("TL") || currentUnit.equals("Stueck") || currentUnit.equals("ml") || currentUnit.equals("Portion")) {
                    Toast.makeText(getApplicationContext(), "Die Einheiten g, EL, TL, Stueck, ml und Portion können nicht gelöscht werden!", Toast.LENGTH_SHORT).show();
                }
                else {
                    DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                    if (db.deleteUnit(currentUnit) == true) {
                        Toast.makeText(getApplicationContext(), currentUnit + " erfolgreich entfernt!", Toast.LENGTH_SHORT).show();
                        fillSpinner(unitList);
                    }
                }
            }

        });

        submitFood.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String foodText = foodName.getText().toString();
                String kcalText = kcal.getText().toString();
                String quantityText = quantity.getText().toString();
                String unitText = unitList.getSelectedItem().toString();

                if (foodText.isEmpty() || kcalText.isEmpty() || quantityText.isEmpty() || unitText.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Bitte das Formular ausfüllen!", Toast.LENGTH_SHORT).show();
                }
                else {
                    DatabaseHandler db = new DatabaseHandler(getApplicationContext());

                    db.insertFood(foodText, Integer.parseInt(kcalText), Integer.parseInt(quantityText), unitText);

                    Toast.makeText(getApplicationContext(), foodText + " wurde hinzugefügt!", Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(view.getContext(), MainActivity.class);
                    startActivity(myIntent);
                }
            }}
        );

    }

    private void fillSpinner(Spinner spinner) {

        DatabaseHandler db = new DatabaseHandler(this);
        db.getReadableDatabase();

        List<String> unitList = new ArrayList<String>();

        unitList = db.getUnits();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, unitList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    }

