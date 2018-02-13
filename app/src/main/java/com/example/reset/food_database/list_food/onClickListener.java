
package com.example.reset.food_database.list_food;

import android.app.Activity;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;


/**
 * Created by Oliver Gras
 */

//manages searchview, listview & clicklisteners for this activity
public class onClickListener implements OnClickListener, android.widget.SearchView.OnQueryTextListener, android.widget.ListView.OnItemClickListener {

    private Activity activity;
    private logic applicationLogic;
    private gui gui;

    public onClickListener(Activity act, gui gui, logic appLogic) {
        super();
        this.gui = gui;
        applicationLogic = appLogic;
        activity = act;
        this.gui.getAddFood().setOnClickListener(this);
        this.gui.getFoodSearch().setOnQueryTextListener(this);
        this.gui.getFoodList().setOnItemClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if (view == gui.getAddFood()) {
            applicationLogic.addFoodButtonClicked();
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
            gui.getFoodList().clearTextFilter();
        } else {
           gui.getFoodList().setFilterText(newText.toString());
        }
            return true;
        }

        }
