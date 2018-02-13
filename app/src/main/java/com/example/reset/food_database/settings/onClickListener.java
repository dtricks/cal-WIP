package com.example.reset.food_database.settings;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * Created by Annabella Peekhaus
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
        this.gui.getSettingsSubmit().setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view == gui.getSettingsSubmit()) {
            applicationLogic.submitSettingsButtonClicked();
        }
    }



}

