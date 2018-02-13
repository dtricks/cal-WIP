
package com.example.reset.food_database.settings;

import android.os.Bundle;


/*
 * Created by Annabella Peekhaus
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
