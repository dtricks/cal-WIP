package com.example.reset.food_database.edit_unit;

import android.widget.Button;
import android.widget.EditText;

import com.example.reset.food_database.R;

/**
 * Created by Annabella Peekhaus
 */

public class gui {

    //initalizing the gui objects
    private EditText editUnitText;
    private Button editUnitSubmit;

    //finding gui objects in layout
    public gui(init activity) {
        activity.setContentView(R.layout.edit_unit);
        editUnitText = (EditText) activity.findViewById(R.id.unit_edit_textfield);
        editUnitSubmit = (Button) activity.findViewById(R.id.submitEditUnitbutton);

    }

    //getter & setter
    public EditText getUnitText() {
        return editUnitText;
    }

    public void setUnitText(EditText unitText) {
        this.editUnitText = unitText;
    }

    public Button getUnitSubmit() {
        return editUnitSubmit;
    }

    public void setUnitSubmit(Button unitSubmit) {
        this.editUnitSubmit = unitSubmit;
    }

}
