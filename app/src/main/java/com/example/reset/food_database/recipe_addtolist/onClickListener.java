
package com.example.reset.food_database.recipe_addtolist;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;


/**
 * Created by Oliver Gras
 */

//manages searchview, listview & clicklisteners for this activity
//public class onClickListener implements android.widget.SearchView.OnQueryTextListener, ListView.OnItemClickListener  {
public class onClickListener implements OnClickListener {

    private Activity activity;
    private logic applicationLogic;
    private com.example.reset.food_database.recipe_addtolist.gui gui;

    public onClickListener(Activity act, com.example.reset.food_database.recipe_addtolist.gui gui, logic appLogic) {
        super();
        this.gui = gui;
        applicationLogic = appLogic;
        activity = act;
        //this.gui.getAddFood().setOnClickListener(this);
        this.gui.getCreaterecipe().setOnClickListener(this);
        this.gui.getRecipename().setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        if (view == gui.getCreaterecipe()) {
            applicationLogic.createRecipeButtonClicked();
        }
    }



/*
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (TextUtils.isEmpty(newText)) {
            gui.getRecipeList().clearTextFilter();
        } else {
            gui.getRecipeList().setFilterText(newText.toString());
        }
        return true;
    }*/

}
