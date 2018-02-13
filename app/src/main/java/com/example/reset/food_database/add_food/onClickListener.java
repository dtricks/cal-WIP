package com.example.reset.food_database.add_food;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * Created by Oliver Gras
 */

//manages the clicklisteners for this activity
public class onClickListener implements OnClickListener {

    private Activity activity;
    private logic applicationLogic;
    private gui gui;

    public onClickListener(Activity act, gui gui, logic appLogic) {
        super();
        this.gui = gui;
        applicationLogic = appLogic;
        activity = act;
        this.gui.getFoodSubmit().setOnClickListener(this);
        this.gui.getDeleteUnit().setOnClickListener(this);
        this.gui.getEditUnit().setOnClickListener(this);
        this.gui.getAddUnit().setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view == gui.getFoodSubmit()) {
            applicationLogic.submitFoodButtonClicked();
        }
        else if (view == gui.getAddUnit()){
            applicationLogic.addUnitButtonClicked();
        }
        else if (view == gui.getDeleteUnit()){
            applicationLogic.deleteUnitButtonClicked();
        }
        else if (view == gui.getEditUnit()){
            applicationLogic.editUnitButtonClicked();
        }
    }
}
