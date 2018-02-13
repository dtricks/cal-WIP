package com.example.reset.food_database;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.reset.food_database.diary.activity_diary;
//import com.example.reset.food_database.list_recipes.activity_list_recipes;

/**
 * Created by Oliver Gras
 */

//logic for the menu bottom menu
public class BaseActivity extends AppCompatActivity  {



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bottom_menu, menu);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        return super.onCreateOptionsMenu(menu);
    }

    //leads to the activities diary, list_food, list_recipes & settings from menu bar at the top of the application
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.diary:
                Intent myIntent = new Intent(getApplicationContext(), com.example.reset.food_database.foodlist_recipe.init.class);
                startActivity(myIntent);
                return true;
            case R.id.myfood:
                Intent myIntent1 = new Intent(getApplicationContext(), com.example.reset.food_database.list_food.init.class);
                startActivity(myIntent1);
                return true;
            case R.id.myrecipes:
                Intent myIntent2 = new Intent(getApplicationContext(), com.example.reset.food_database.list_recipes.init.class);
                startActivity(myIntent2);
                return true;
            case R.id.mymaxkcal:
                Intent myIntent3 = new Intent(getApplicationContext(), com.example.reset.food_database.settings.init.class);
                startActivity(myIntent3);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



}
