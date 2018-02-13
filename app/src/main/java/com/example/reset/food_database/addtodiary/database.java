package com.example.reset.food_database.addtodiary;

import android.os.Bundle;

import com.example.reset.food_database.DatabaseHandler;
import com.example.reset.food_database.addtodiary.init;
import com.example.reset.food_database.objects.DiaryEntry;
import com.example.reset.food_database.objects.Unit;

import java.util.Date;
import java.util.List;

/**
 * Created by Denis Kerner
 */

public class database {

    private com.example.reset.food_database.addtodiary.init activity;



    public database(com.example.reset.food_database.addtodiary.init activ, Bundle savedInstanceState) {
        super();
        setActivity(activ);
    }

    com.example.reset.food_database.addtodiary.init getActivity() {
        return activity;
    }

    void setActivity(init activity) {
        this.activity = activity;
    }

    public void insertDiaryEntry(String foodName, int kcal, double portion, Unit unit, Date date, double quantity){
        DatabaseHandler db = new DatabaseHandler(activity);
        db.insertDiaryEntry( foodName,  kcal,  portion,  unit,  date, quantity);
    }


}