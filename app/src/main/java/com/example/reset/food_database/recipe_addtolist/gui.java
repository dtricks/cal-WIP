
package com.example.reset.food_database.recipe_addtolist;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.reset.food_database.R;


/**
 * Created by Oliver Gras
 */


public class gui {

    //initalizing the gui objects
    private TextView textview;
    private Button createrecipe;
    private EditText recipename;

    //finding gui objects in layout
    public gui(init activity) {
        activity.setContentView(R.layout.recipe_addtolist);

        textview = (TextView) activity.findViewById(R.id.textview);
        createrecipe = (Button) activity.findViewById(R.id.createrecipe);
        recipename = (EditText) activity.findViewById(R.id.name_textfield);

    }

    //getter & setter
    public Button getCreaterecipe() {
        return createrecipe;
    }

    public void setCreaterecipe(Button createrecipe) {
        this.createrecipe = createrecipe;
    }

    public EditText getRecipename() {
        return recipename;
    }

    public void setRecipename(EditText recipename) {
        this.recipename = recipename;
    }


}

