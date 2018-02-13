package com.example.reset.food_database.add_unit;

import android.widget.Button;
import android.widget.EditText;

import com.example.reset.food_database.R;

/**
 * Created by Annabella Peekhaus
 */

public class gui {

    //initalizing the gui objects
    private EditText unitText;
    private Button unitSubmit;

    //finding gui objects in layout
    public gui(init activity) {
        activity.setContentView(R.layout.add_unit);
        unitText = (EditText) activity.findViewById(R.id.unit_textfield);
        unitSubmit = (Button) activity.findViewById(R.id.submitunitbutton);

    }

    //getter & setter
    public EditText getUnitText() {
        return unitText;
    }

    public void setUnitText(EditText unitText) {
        this.unitText = unitText;
    }

    public Button getUnitSubmit() {
        return unitSubmit;
    }

    public void setUnitSubmit(Button unitSubmit) {
        this.unitSubmit = unitSubmit;
    }

}

