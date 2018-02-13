package com.example.reset.food_database.diary;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.reset.food_database.R;
import com.example.reset.food_database.diary.init;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.microedition.khronos.egl.EGLDisplay;

/**
 * Created by Denis Kerner
 */

public class gui {

    //initalizing the gui objects
    private SearchView date_filter;
    private ProgressBar progress_bar;
    private TextView progressBar_textview;
    private ListView diary_list_view;
    private Button add_food_button;
    private Button add_recipe_button;

    Calendar myCalendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener date= new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, day);
            updateLabel();
        }
    };



    //finding gui objects in layout
    public gui(final init activity) {
        activity.setContentView(R.layout.diary);
        date_filter = (SearchView) activity.findViewById(R.id.date_filter);
        progress_bar = (ProgressBar) activity.findViewById(R.id.progressBar);
        progressBar_textview = (TextView) activity.findViewById(R.id.progressBarText);
        diary_list_view = (ListView) activity.findViewById(R.id.diarylist);
        add_food_button = (Button) activity.findViewById(R.id.addfoodtodiary);
        add_recipe_button = (Button) activity.findViewById(R.id.addrecipetodiary);

        add_food_button .setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), com.example.reset.food_database.list_food.init.class);
                activity.startActivity(myIntent);
            }
        });

        add_recipe_button .setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), com.example.reset.food_database.list_recipes.activity_list_recipes.class);
                activity.startActivity(myIntent);
            }
        });

        date_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new DatePickerDialog(activity , date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH) ).show();

            }
        });

    }

    private void updateLabel(){
        String format="dd.MM.yyyy";
        SimpleDateFormat sdf= new SimpleDateFormat(format);

        date_filter.setQuery(sdf.format(myCalendar.getTime()), true);
    }

    //getters & setters

    public SearchView getDate_filter() {
        return date_filter;
    }

    public void setDate_filter(SearchView date_filter) {
        this.date_filter = date_filter;
    }

    public ProgressBar getProgress_bar() {
        return progress_bar;
    }

    public void setProgress_bar(ProgressBar progress_bar) {
        this.progress_bar = progress_bar;
    }

    public TextView getProgressBar_textview() {
        return progressBar_textview;
    }

    public void setProgressBar_textview(TextView progressBar_textview) {
        this.progressBar_textview = progressBar_textview;
    }

    public ListView getDiary_list_view() {
        return diary_list_view;
    }

    public void setDiary_list_view(ListView diary_list_view) {
        this.diary_list_view = diary_list_view;
    }

    public Button getAdd_food_button() {
        return add_food_button;
    }

    public void setAdd_food_button(Button add_food_button) {
        this.add_food_button = add_food_button;
    }

    public Button getAdd_recipe_button() {
        return add_recipe_button;
    }

    public void setAdd_recipe_button(Button add_recipe_button) {
        this.add_recipe_button = add_recipe_button;
    }
}
