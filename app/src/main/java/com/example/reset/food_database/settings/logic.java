package com.example.reset.food_database.settings;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import com.example.reset.food_database.DatabaseHandler;


/*
 * Created by Annabella Peekhaus
 */

//logic for setting the daily kalories goal
public class logic {

    private Activity activity;
    private database data;
    private gui gui;

    public logic(Activity act, database data, gui gui) {
        super();
        this.data = data;
        this.gui = gui;
        activity = act;

        DatabaseHandler db = new DatabaseHandler(activity);
        db.getReadableDatabase();
        gui.getSettingsText().setText(String.valueOf(db.getSettings()));

    }

    //clicking the button updates the kalories goal in the database
    public void submitSettingsButtonClicked(){

        DatabaseHandler db = new DatabaseHandler(activity);
        db.getReadableDatabase();

        db.updateSettings(Integer.parseInt(gui.getSettingsText().getText().toString()));
        int newkcal = db.getSettings();

        Toast.makeText(activity, "Your daily calories goal has been set to " + newkcal + " !" , Toast.LENGTH_SHORT).show();
    }
}