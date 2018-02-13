/*
package com.example.reset.food_database.add_unit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.reset.food_database.BaseActivity;
import com.example.reset.food_database.DatabaseHandler;
import com.example.reset.food_database.R;
import com.example.reset.food_database.add_food.activity_add_food;

public class activity_add_unit extends BaseActivity {

    EditText unit;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_unit);

        unit = (EditText)findViewById(R.id.unit_textfield);
        submit = (Button)findViewById(R.id.submitunitbutton);


        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String indicatedUnit =  unit.getText().toString();
                DatabaseHandler db = new DatabaseHandler(getApplicationContext());

                if (indicatedUnit.matches("")){
                    Toast.makeText(getApplicationContext(), "Bitte eine Einheit eingeben!", Toast.LENGTH_SHORT).show();

                }
                else if (db.doesUnitExist(indicatedUnit) == true){
                    Toast.makeText(getApplicationContext(), "Einheit existiert bereits!", Toast.LENGTH_SHORT).show();

                }
                else {
                    db.insertUnit(db.getWritableDatabase(), indicatedUnit);

                    Toast.makeText(getApplicationContext(), "Die Einheit " + indicatedUnit + " wurde hinzugefügt!", Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(view.getContext(), activity_add_food.class);
                    startActivity(myIntent);
                }
            }

        });

    }
}*/
