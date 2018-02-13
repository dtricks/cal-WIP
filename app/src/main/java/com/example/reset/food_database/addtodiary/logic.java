package com.example.reset.food_database.addtodiary;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.reset.food_database.DatabaseHandler;
import com.example.reset.food_database.addtodiary.database;
import com.example.reset.food_database.addtodiary.gui;
import com.example.reset.food_database.objects.Unit;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Denis Kerner
 */


//logic for adding a unit to the unit list/spinner
public class logic {

        private Activity activity;
        private com.example.reset.food_database.addtodiary.database data;
        private gui gui;

        public logic(Activity act, database data, gui gui) {
            super();
            this.data = data;
            this.gui = gui;
            activity = act;

            Intent intent = activity.getIntent();


        }

    public database getData() {
        return data;
    }

    public void setData(database data) {
        this.data = data;
    }

    //submit unit to unitlist/spinner
        public void submitFoodButtonClicked() {

        String givenDate =  gui.getDate_textfield().getText().toString();
        Double givenPortion =  Double.parseDouble(gui.getPortion_textfield().getText().toString());


        if (givenDate.matches("")){
            Toast.makeText(activity, "Please insert a Date!", Toast.LENGTH_SHORT).show();

        }
        if (givenPortion.isNaN() || givenPortion.equals(0)){
            Toast.makeText(activity, "Please insert a valid Portion!", Toast.LENGTH_SHORT).show();

        }
        else {

            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy"); //TODO help for below -- Date format
            Date date = new Date();

            try{
                date=dateFormat.parse(gui.getDate_textfield().getText().toString());
            }catch (Exception e){Toast.makeText(activity, e.getMessage(), Toast.LENGTH_LONG);}



            data.insertDiaryEntry(
            gui.getName_content_textview().getText().toString(),
            Integer.parseInt(gui.getKcal_content_textview().getText().toString()),
            givenPortion,
            new Unit(gui.getUnit_content_textview().getText().toString()),
            date, Double.parseDouble(gui.getQuantity_content_textview().getText().toString())); //TODO Date format

            Toast.makeText(activity, "Diary Entry for " + gui.getName_content_textview().getText().toString() + " has been added!", Toast.LENGTH_SHORT).show();
            Intent intent = activity.getIntent();
            Intent myIntent = new Intent(activity, com.example.reset.food_database.diary.init.class);
            /*if (intent != null) {
                myIntent.putExtra("name", intent.getStringExtra("name"));
                myIntent.putExtra("quantity", intent.getStringExtra("quantity"));
                myIntent.putExtra("kcal", intent.getStringExtra("kcal"));
                myIntent.putExtra("date", givenDate);
                myIntent.putExtra("portion", givenPortion);
            }*/
            activity.startActivity(myIntent);
        }


    }



}


