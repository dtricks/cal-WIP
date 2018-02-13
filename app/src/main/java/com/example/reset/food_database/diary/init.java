package com.example.reset.food_database.diary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.reset.food_database.BaseActivity;
import com.example.reset.food_database.DatabaseHandler;
import com.example.reset.food_database.diary.database;
import com.example.reset.food_database.diary.gui;
import com.example.reset.food_database.diary.logic;
import com.example.reset.food_database.diary.onClickListener;
import com.example.reset.food_database.objects.DiaryEntry;
import com.example.reset.food_database.objects.Food;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Created by Denis Kerner
 */

//manages the classes within the package
public class init extends BaseActivity {

    private logic applicationLogic;
    private database data;
    private onClickListener eventListener;
    private gui gui;
    public Activity activity;

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

        initApplicationLogic(this);
        initEventToListenerMapping(this);
        applicationLogic.setupDatabase();
        applicationLogic.setTextViewContents(this);
        applicationLogic.setProgressBar(this);
        setCurrentDateToTextField(this);


    }


    public int getSumOfKcalInDiaryList(Activity activity){
        return applicationLogic.getKcalTotal(activity);
    }



    public void setCurrentDateToTextField(Activity activity){
        SimpleDateFormat sdf= new SimpleDateFormat("dd.MM.yyyy");
        Date today= new Date();
        today.setHours(0);
        gui.getDate_filter().setQuery(sdf.format(today), true);
    }

}
