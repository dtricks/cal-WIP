/*
package com.example.reset.food_database.settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.reset.food_database.BaseActivity;
import com.example.reset.food_database.DatabaseHandler;
import com.example.reset.food_database.R;
import com.example.reset.food_database.add_food.init;

*/
/**
 * Created by C4RL0zZ0 on 03.02.2018.
 *//*


public class settings extends BaseActivity {

    Button submitSettings;

    EditText maxkcal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        submitSettings = (Button) findViewById(R.id.submitSettings);
        maxkcal = (EditText) findViewById(R.id.kcalmax);

        DatabaseHandler db = new DatabaseHandler(this);
        db.getReadableDatabase();
        maxkcal.setText(String.valueOf(db.getSettings()));

        submitSettings .setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                db.getReadableDatabase();
                db.updateSettings(Integer.parseInt(maxkcal.getText().toString()));
                int newkcal = db.getSettings();

                Toast.makeText(settings.this, "Your daily calories goal has been set to " + newkcal + " !" , Toast.LENGTH_SHORT).show();
            }

        });
    }


}
*/
