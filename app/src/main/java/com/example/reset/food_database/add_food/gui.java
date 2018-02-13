package com.example.reset.food_database.add_food;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.reset.food_database.R;

/**
 * Created by Oliver Gras
 */

public class gui {

    //initalizing the gui objects
    private EditText foodnameText;
    private EditText kcalText;
    private EditText quantityText;
    private Spinner unitSpinner;
    private ImageButton addUnit;
    private ImageButton deleteUnit;
    private ImageButton editUnit;
    private Button  foodSubmit;

    //finding gui objects in layout
    public gui(init activity) {
        activity.setContentView(R.layout.add_food);
        foodnameText = (EditText) activity.findViewById(R.id.name_textfield);
        kcalText = (EditText) activity.findViewById(R.id.kcal_textfield);
        quantityText = (EditText) activity.findViewById(R.id.quantity_textfield);
        unitSpinner = (Spinner) activity.findViewById(R.id.unit_spinner);
        addUnit = (ImageButton) activity.findViewById(R.id.addunit);
        deleteUnit = (ImageButton) activity.findViewById(R.id.deleteunit);
        editUnit = (ImageButton) activity.findViewById(R.id.editunit);
        foodSubmit = (Button) activity.findViewById(R.id.submitfoodbutton);

    }

    //getter & setter
    public EditText getFoodnameText() {
        return foodnameText;
    }

    public void setFoodnameText(EditText foodnameText) {
        this.foodnameText = foodnameText;
    }

    public EditText getKcalText() {
        return kcalText;
    }

    public void setKcalText(EditText kcalText) {
        this.kcalText = kcalText;
    }

    public EditText getQuantityText() {
        return quantityText;
    }

    public void setQuantityText(EditText quantityText) {
        this.quantityText = quantityText;
    }


    public Spinner getUnitSpinner() {
        return unitSpinner;
    }

    public void setUnitSpinner(Spinner unitSpinner) {
        this.unitSpinner = unitSpinner;
    }

    public ImageButton getAddUnit() {
        return addUnit;
    }

    public void setAddUnit(ImageButton addUnit) {
        this.addUnit = addUnit;
    }

    public ImageButton getDeleteUnit() {
        return deleteUnit;
    }

    public void setDeleteUnit(ImageButton deleteUnit) {
        this.deleteUnit = deleteUnit;
    }

    public ImageButton getEditUnit() {
        return editUnit;
    }

    public void setEditUnit(ImageButton editUnit) {
        this.editUnit = editUnit;
    }

    public Button getFoodSubmit() {
        return foodSubmit;
    }

    public void setFoodSubmit(Button foodSubmit) {
        this.foodSubmit = foodSubmit;
    }




}
