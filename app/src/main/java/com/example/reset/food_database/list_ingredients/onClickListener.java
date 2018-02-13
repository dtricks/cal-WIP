
package com.example.reset.food_database.list_ingredients;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;


/**
 * Created by Oliver Gras
 */

//manages searchview, listview & clicklisteners for this activity
//public class onClickListener implements android.widget.SearchView.OnQueryTextListener, ListView.OnItemClickListener  {
public class onClickListener implements OnClickListener, android.widget.SearchView.OnQueryTextListener, ListView.OnItemClickListener {

    private Activity activity;
    private logic applicationLogic;
    private com.example.reset.food_database.list_ingredients.gui gui;

    public onClickListener(Activity act, com.example.reset.food_database.list_ingredients.gui gui, logic appLogic) {
        super();
        this.gui = gui;
        applicationLogic = appLogic;
        activity = act;
        //this.gui.getAddFood().setOnClickListener(this);
        this.gui.getAddIngredient().setOnClickListener(this);
        this.gui.getIngredientfilter().setOnQueryTextListener(this);
        this.gui.getIngredientList().setOnItemClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if (view == gui.getAddIngredient()) {
            applicationLogic.addRecipeButtonClicked();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final String selectedItem = (String) parent.getItemAtPosition(position);
        applicationLogic.itemClicked(selectedItem);
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (TextUtils.isEmpty(newText)) {
            gui.getIngredientList().clearTextFilter();
        } else {
            gui.getIngredientList().setFilterText(newText.toString());
        }
        return true;
    }

}
