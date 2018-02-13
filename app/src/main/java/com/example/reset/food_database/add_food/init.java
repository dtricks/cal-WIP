package com.example.reset.food_database.add_food;

import android.app.Activity;
import android.os.Bundle;

import com.example.reset.food_database.BaseActivity;

/**
 * Created by Oliver Gras
 */

//manages the classes within the package
public class init extends BaseActivity {

    private logic applicationLogic;
    private database data;
    private onClickListener eventListener;
    private gui gui;

    private void initApplicationLogic(Activity activity) {
        applicationLogic = new logic(activity, data, gui);
    }

    private void initData(Bundle savedInstanceState) {
        data = new database(this, savedInstanceState);
    }

    private void initEventToListenerMapping(Activity act) {
        eventListener = new onClickListener(act, gui, applicationLogic);
    }

    private void initGUI() {
        gui = new gui(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData(savedInstanceState);
        initGUI();
        initApplicationLogic(this);
        initEventToListenerMapping(this);

    }
}
