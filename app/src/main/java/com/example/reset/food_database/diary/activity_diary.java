package com.example.reset.food_database.diary;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.reset.food_database.BaseActivity;
import com.example.reset.food_database.DatabaseHandler;
import com.example.reset.food_database.R;
import com.example.reset.food_database.objects.Food;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Denis on 03.02.2018.
 */


    public class activity_diary extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary);

    }
}