
package com.example.reset.food_database.statistics;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.example.reset.food_database.BaseActivity;


/**
 * Created by Denis Kerner
 */

//manages the classes within the package
public class init extends BaseActivity {

    private logic applicationLogic;
    private database data;
    private onClickListener eventListener;
    private com.example.reset.food_database.statistics.gui gui;

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
        setLast7Days(this);
        setLast14Days(this);
        setLast30Days(this);

    }



    public void setLast7Days(Activity activity){
        double averageLast7days=applicationLogic.calculateAvgForDays(activity, 7);
        Log.d("statistics.init", "Average: "+averageLast7days);
        //print
        String output=String.format("%.2f", averageLast7days);
        gui.getLast7days().setText(output);

    }

    public void setLast14Days(Activity activity){
        double averageLast14days=applicationLogic.calculateAvgForDays(activity, 14);
        Log.d("statistics.init", "Average: "+averageLast14days);
        //print
        String output=String.format("%.2f", averageLast14days);
        gui.getLast14days().setText(output);

    }

    public void setLast30Days(Activity activity){
        double averageLast30days=applicationLogic.calculateAvgForDays(activity, 30);
        Log.d("statistics.init", "Average: "+averageLast30days);
        //print
        String output=String.format("%.2f", averageLast30days);
        gui.getLast30days().setText(output);

    }

}

