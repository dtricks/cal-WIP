
package com.example.reset.food_database.settings;

import android.widget.Button;
import android.widget.EditText;

import com.example.reset.food_database.R;


/*
 * Created by Annabella Peekhaus
 */


public class gui {

    //initalizing the gui objects
    private EditText settingsText;
    private Button settingsSubmit;

    //finding gui objects in layout
    public gui(init activity) {
        activity.setContentView(R.layout.settings);
        settingsText = (EditText) activity.findViewById(R.id.kcalmax);
        settingsSubmit = (Button) activity.findViewById(R.id.submitSettings);

    }

    //getter & setter
    public EditText getSettingsText() {
        return settingsText;
    }

    public void setSettingsText(EditText settingsText) {
        this.settingsText = settingsText;
    }

    public Button getSettingsSubmit() {
        return settingsSubmit;
    }

    public void setSettingsSubmit(Button settingsSubmit) {
        this.settingsSubmit = settingsSubmit;
    }
}

