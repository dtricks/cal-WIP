
package com.example.reset.food_database.edit_diaryEntry;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;


/**
 * Created by Denis Kerner
 */

//manages the clicklisteners for this activity
public class onClickListener implements OnClickListener {

    private Activity activity;
    private logic applicationLogic;
    private com.example.reset.food_database.edit_diaryEntry.gui gui;

    public onClickListener(Activity act, com.example.reset.food_database.edit_diaryEntry.gui gui, logic appLogic) {
        super();
        this.gui = gui;
        applicationLogic = appLogic;
        activity = act;
        this.gui.getSubmitfoodbutton().setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view == gui.getSubmitfoodbutton()) {
            applicationLogic.submitFoodEditButtonClicked();
        }
    }
}

