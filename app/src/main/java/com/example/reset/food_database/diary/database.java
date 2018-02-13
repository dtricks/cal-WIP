package com.example.reset.food_database.diary;

import android.os.Bundle;

import com.example.reset.food_database.diary.init;
import com.example.reset.food_database.objects.DiaryEntry;

import java.util.List;

/**
 * Created by Denis Kerner
 */

public class database {

    private init activity;

    List<DiaryEntry> completeListFromDB;
    List<DiaryEntry> currentlyFilteredList;

    public database(init activ, Bundle savedInstanceState) {
        super();
        setActivity(activ);
    }

    init getActivity() {
        return activity;
    }

    void setActivity(init activity) {
        this.activity = activity;
    }

    public List<DiaryEntry> getCompleteListFromDB() {
        return completeListFromDB;
    }

    public void setCompleteListFromDB(List<DiaryEntry> completeListFromDB) {
        this.completeListFromDB = completeListFromDB;
    }

    public List<DiaryEntry> getCurrentlyFilteredList() {
        return currentlyFilteredList;
    }

    public void setCurrentlyFilteredList(List<DiaryEntry> currentlyFilteredList) {
        this.currentlyFilteredList = currentlyFilteredList;
    }
}