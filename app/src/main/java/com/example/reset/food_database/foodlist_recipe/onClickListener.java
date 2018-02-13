package com.example.reset.food_database.foodlist_recipe;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.support.v7.widget.SearchView;

/**
 * Created by Oliver Gras
 */

//manages searchview and listview for this activity
public class onClickListener implements android.widget.SearchView.OnQueryTextListener, android.widget.ListView.OnItemClickListener  {

    private Activity activity;
    private logic applicationLogic;
    private gui gui;

    public onClickListener(Activity act, gui gui, logic appLogic) {
        super();
        this.gui = gui;
        applicationLogic = appLogic;
        activity = act;
        this.gui.getFoodlistSearchRecipe().setOnQueryTextListener(this);
        this.gui.getFoodListRecipe().setOnItemClickListener(this);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final String selectedItem = (String) parent.getItemAtPosition(position);
        applicationLogic.foodlistRecipeItemClicked(selectedItem);
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (TextUtils.isEmpty(newText)) {
            gui.getFoodListRecipe().clearTextFilter();
        } else {
            gui.getFoodListRecipe().setFilterText(newText.toString());
        }
        return true;
    }
}
