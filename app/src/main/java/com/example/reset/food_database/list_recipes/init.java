package com.example.reset.food_database.list_recipes;

import android.app.Activity;
import android.os.Bundle;

import com.example.reset.food_database.BaseActivity;


//manages the classes within the package

/**
 * Created by Oliver Gras
 */


public class init extends BaseActivity {

    private com.example.reset.food_database.list_recipes.logic applicationLogic;
    private com.example.reset.food_database.list_recipes.database data;
    private com.example.reset.food_database.list_recipes.onClickListener eventListener;
    private com.example.reset.food_database.list_recipes.gui gui;

    private void initApplicationLogic(Activity activity) {
        applicationLogic = new com.example.reset.food_database.list_recipes.logic(activity, data, gui);
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

