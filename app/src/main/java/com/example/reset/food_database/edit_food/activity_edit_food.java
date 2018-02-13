/*
package com.example.reset.food_database.edit_food;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.reset.food_database.BaseActivity;
import com.example.reset.food_database.DatabaseHandler;
import com.example.reset.food_database.R;
import com.example.reset.food_database.list_food.list_food;
import com.example.reset.food_database.objects.Food;
import com.example.reset.food_database.objects.Unit;

import java.util.ArrayList;
import java.util.List;


public class activity_edit_food extends BaseActivity {

    EditText foodName;
    EditText kcal;
    EditText quantity;
    Spinner unitListSpinner;
    Food currentFood;

    Button editFood;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_food);

        foodName = (EditText) findViewById(R.id.name_textfield_edit);
        kcal = (EditText) findViewById(R.id.kcal_textfield_edit);
        quantity = (EditText) findViewById(R.id.quantity_textfield_edit);
        unitListSpinner = (Spinner) findViewById(R.id.unit_spinner_edit);
        editFood = (Button)findViewById(R.id.foodbutton_edit);

        Intent intent = getIntent();
        if (intent != null) {
            //int handoverId = intent.getIntExtra("handoverId",0);
            //quantity.setText(String.valueOf(intent.getIntExtra("handoverId",0)));
            DatabaseHandler db = new DatabaseHandler(this);
            db.getReadableDatabase();
            currentFood = db.getFood_new(intent.getIntExtra("handoverId", 0 ));
        }

        quantity.setText(String.valueOf(currentFood.getQuantity()));
        kcal.setText(String.valueOf(currentFood.getKcal()));
        foodName.setText(String.valueOf(currentFood.getName()));

        fillSpinner(unitListSpinner);

        unitListSpinner.setSelection(getIndex(unitListSpinner, currentFood.getUnit().getName()));


        //unitListSpinner.getSelectedItemPosition(intent.getIntExtra("handoverId", 0 ));

           // quantity.setText(handoverId);


        editFood.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String foodText = foodName.getText().toString();
                String kcalText = kcal.getText().toString();
                String quantityText = quantity.getText().toString();

                int unitText = unitList.get(unitListSpinner.getSelectedItemPosition()).getId();

                if (foodText.isEmpty() || kcalText.isEmpty() || quantityText.isEmpty() ) {
                    Toast.makeText(getApplicationContext(), "Please fill in the whole formular!", Toast.LENGTH_SHORT).show();
                }
                else {
                    DatabaseHandler db = new DatabaseHandler(getApplicationContext());

                    db.updateFood(currentFood.getId(),foodText, Integer.parseInt(kcalText), Double.parseDouble(quantityText), unitText);

                    Toast.makeText(getApplicationContext(), foodText + " has been edited!", Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(view.getContext(), list_food.class);
                    startActivity(myIntent);
                }
            }}
        );


    }

    List<Unit> unitList = new ArrayList<Unit>();

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

    //private method of your class
    private int getIndex(Spinner spinner, String unitname)
    {
        int index = 0;

        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).equals(unitname)){
                index = i;
                break;
            }
        }
        return index;
    }





}

*/
