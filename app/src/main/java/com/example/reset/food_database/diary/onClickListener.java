package com.example.reset.food_database.diary;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;

import com.example.reset.food_database.DatabaseHandler;
import com.example.reset.food_database.diary.logic;
import com.example.reset.food_database.objects.DiaryEntry;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Denis Kerner
 */

//manages the clicklisteners for this activity
public class onClickListener implements OnClickListener, android.widget.ListView.OnItemClickListener , android.widget.SearchView.OnQueryTextListener  {

    private Activity activity;
    private logic applicationLogic;
    private com.example.reset.food_database.diary.gui gui;


    public onClickListener(Activity act, com.example.reset.food_database.diary.gui gui, logic appLogic) {
        super();
        this.gui = gui;
        applicationLogic = appLogic;
        activity = act;
        //this.gui.getSubmitfoodbutton().setOnClickListener(this);
        this.gui.getDiary_list_view().setOnItemClickListener(this);
        this.gui.getDate_filter().setOnQueryTextListener(this);

    }

    @Override
    public void onClick(View view) {

        if (view == gui.getAdd_food_button()) {
            applicationLogic.addFoodButtonClicked(); //TODO Buttons aren't doing anything
        }
        if (view == gui.getAdd_recipe_button()){
            applicationLogic.addRecipeButtonClicked();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final String selectedItem = (String) parent.getItemAtPosition(position);
        applicationLogic.itemClicked(selectedItem);
    }


    @Override
    public boolean onQueryTextSubmit(String s) {

        SimpleDateFormat sdf= new SimpleDateFormat("dd.MM.yyyy");
        DatabaseHandler db= new DatabaseHandler(activity);
        applicationLogic.getData().setCompleteListFromDB(db.getDiaryEntryList());
        if (s.isEmpty())
            {applicationLogic.fillList(applicationLogic.getData().getCompleteListFromDB());
            applicationLogic.getData().setCurrentlyFilteredList(applicationLogic.getData().getCompleteListFromDB());
            applicationLogic.setProgressBar(activity);
            applicationLogic.setTextViewContents(activity, applicationLogic.getKcalOfCurrentlyFiltered());
            return true;
            }
        applicationLogic.getData().setCurrentlyFilteredList(new ArrayList<DiaryEntry>());
        for (DiaryEntry diaryEntry: applicationLogic.getData().getCompleteListFromDB()
                ) {
            if (sdf.format(diaryEntry.getDate()).equals(s)){
                applicationLogic.getData().getCurrentlyFilteredList().add(diaryEntry);
            }
        }
        if(applicationLogic.getData().getCurrentlyFilteredList().isEmpty()){
            applicationLogic.fillList(applicationLogic.getData().getCurrentlyFilteredList());
            applicationLogic.setProgressBar(activity);
            applicationLogic.setTextViewContents(activity, applicationLogic.getKcalOfCurrentlyFiltered());
            return false;
        }else{
            applicationLogic.fillList(applicationLogic.getData().getCurrentlyFilteredList());
            applicationLogic.setProgressBar(activity);
            applicationLogic.setTextViewContents(activity, applicationLogic.getKcalOfCurrentlyFiltered());
            return true;
        }

    }

    @Override
    public boolean onQueryTextChange(String s) {
        if (s.isEmpty())
            applicationLogic.fillList(applicationLogic.getData().getCompleteListFromDB());
        int kcalOfCUrrentlyFiltered=applicationLogic.getKcalOfCurrentlyFiltered();
        applicationLogic.setTextViewContents(activity, kcalOfCUrrentlyFiltered);
        applicationLogic.setProgressBar(activity);
        return false;
    }
}
