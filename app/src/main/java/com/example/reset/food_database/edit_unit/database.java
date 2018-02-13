package com.example.reset.food_database.edit_unit;

import android.os.Bundle;

/**
 * Created by C4RL0zZ0 on 09.02.2018.
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
