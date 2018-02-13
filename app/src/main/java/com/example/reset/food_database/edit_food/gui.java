
package com.example.reset.food_database.edit_food;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.reset.food_database.R;


/**
 * Created by Oliver Gras
 */


public class gui {

    //initalizing the gui objects
    private EditText foodnameEditText;
    private EditText kcalEditText;
    private EditText quantityEditText;
    private Spinner unitEditSpinner;
    private Button foodEditSubmit;

    //finding gui objects in layout
    public gui(init activity) {
        activity.setContentView(R.layout.edit_food);
        foodnameEditText = (EditText) activity.findViewById(R.id.name_textfield_edit);
        kcalEditText = (EditText) activity.findViewById(R.id.kcal_textfield_edit);
        quantityEditText = (EditText) activity.findViewById(R.id.quantity_textfield_edit);
        unitEditSpinner = (Spinner) activity.findViewById(R.id.unit_spinner_edit);
        foodEditSubmit = (Button) activity.findViewById(R.id.foodbutton_edit);

    }

    //getter & setter
    public EditText getFoodnameEditText() {
        return foodnameEditText;
    }

    public void setFoodnameEditText(EditText foodnameEditText) {
        this.foodnameEditText = foodnameEditText;
    }

    public EditText getKcalEditText() {
        return kcalEditText;
    }

    public void setKcalEditText(EditText kcalEditText) {
        this.kcalEditText = kcalEditText;
    }

    public EditText getQuantityEditText() {
        return quantityEditText;
    }

    public void setQuantityEditText(EditText quantityEditText) {
        this.quantityEditText = quantityEditText;
    }

    public Spinner getUnitEditSpinner() {
        return unitEditSpinner;
    }

    public void setUnitEditSpinner(Spinner unitEditSpinner) {
        this.unitEditSpinner = unitEditSpinner;
    }

    public Button getFoodEditSubmit() {
        return foodEditSubmit;
    }

    public void setFoodEditSubmit(Button foodEditSubmit) {
        this.foodEditSubmit = foodEditSubmit;
    }
}

