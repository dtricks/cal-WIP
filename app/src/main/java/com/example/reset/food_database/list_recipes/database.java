package com.example.reset.food_database.list_recipes;

import android.os.Bundle;


/**
 * Created by Oliver Gras
 */


public class database {

    private com.example.reset.food_database.list_recipes.init activity;

    public database(com.example.reset.food_database.list_recipes.init activ, Bundle savedInstanceState) {
        super();
        setActivity(activ);
    }

    com.example.reset.food_database.list_recipes.init getActivity() {
        return activity;
    }

    void setActivity(com.example.reset.food_database.list_recipes.init activity) {
        this.activity = activity;
    }
}

