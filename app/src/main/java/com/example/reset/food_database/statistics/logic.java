package com.example.reset.food_database.statistics;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.example.reset.food_database.DatabaseHandler;
import com.example.reset.food_database.objects.DiaryEntry;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/*
 * Created by Denis Kerner
 */

//logic for setting the daily kalories goal
public class logic {

    private Activity activity;
    private database data;
    private com.example.reset.food_database.statistics.gui gui;

    public logic(Activity act, database data, com.example.reset.food_database.statistics.gui gui) {
        super();
        this.data = data;
        this.gui = gui;
        activity = act;



    }

    public double calculateAvgForDays(Activity activity, int days){
        double averageLastDays=0;
        //get all diaryEntries from db
        DatabaseHandler db = new DatabaseHandler(activity);
        db.getReadableDatabase();
        List<DiaryEntry> diaryEntryList= new ArrayList<>();
        diaryEntryList=db.getDiaryEntryList();
        //get current Date
        Date today= new Date();
        today.setHours(0);

        //go through all diaryEntries
        for (DiaryEntry object: diaryEntryList) {
            //if date is within last given days
            long timeshift=days* 1000*60*60*24;
            if (object.getDate().after(new Date(today.getTime()- (timeshift)))){
                //add all kcal values
                averageLastDays+=(object.getKcal()*object.getPortion());
            }
        }

        //divide by days
        Log.d("statistics.logic", "SUM: "+averageLastDays+ " Days: "+(double)days);
        averageLastDays/=(double) days;
        return averageLastDays;
    }
}