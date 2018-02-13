
package com.example.reset.food_database.list_food;

import android.os.Bundle;


/**
 * Created by Oliver Gras
 */


public class database {

    private init activity;

    public database(init activ, Bundle savedInstanceState) {
        super();
        setActivity(activ);
    }

    init getActivity() {
        return activity;
    }

    void setActivity(init activity) {
        this.activity = activity;
    }
}

