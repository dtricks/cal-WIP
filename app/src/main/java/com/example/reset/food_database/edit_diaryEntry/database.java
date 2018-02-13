
package com.example.reset.food_database.edit_diaryEntry;

import android.os.Bundle;


/**
 * Created by Denis Kerner
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

