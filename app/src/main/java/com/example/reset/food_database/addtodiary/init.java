package com.example.reset.food_database.addtodiary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.reset.food_database.BaseActivity;
import com.example.reset.food_database.DatabaseHandler;
import com.example.reset.food_database.addtodiary.database;
import com.example.reset.food_database.addtodiary.gui;
import com.example.reset.food_database.addtodiary.logic;
import com.example.reset.food_database.addtodiary.onClickListener;
import com.example.reset.food_database.objects.DiaryEntry;
import com.example.reset.food_database.objects.Food;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Denis Kerner
 */

//manages the classes within the package
public class init extends BaseActivity {

    private com.example.reset.food_database.addtodiary.logic applicationLogic;
    private database data;
    private onClickListener eventListener;
    private gui gui;

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
        setTextViewContents();
        initApplicationLogic(this);
        initEventToListenerMapping(this);

        setCurrentDateToTextField(this);

    }

    public void setTextViewContents(){
        //get data from intent
        Intent intent= this.getIntent();
        DatabaseHandler db = new DatabaseHandler(this);
        db.getReadableDatabase();
        Food currentFood = db.getFood_new(intent.getIntExtra("handoverId", 0));

        //put data into textViews
        gui.getName_content_textview().setText(currentFood.getName());
        gui.getKcal_content_textview().setText(String.format("%d",currentFood.getKcal()));
        gui.getUnit_content_textview().setText(currentFood.getUnit().getName());
        gui.getQuantity_content_textview().setText(String.format( "%1$,.2f", currentFood.getQuantity()));
    }

    public void setCurrentDateToTextField(Activity activity){
        SimpleDateFormat sdf= new SimpleDateFormat("dd.MM.yyyy");
        Date today= new Date();
        today.setHours(0);
        gui.getDate_textfield().setText(sdf.format(today));
    }
}
