
package com.example.reset.food_database.edit_diaryEntry;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.reset.food_database.DatabaseHandler;
import com.example.reset.food_database.objects.DiaryEntry;
import com.example.reset.food_database.objects.Food;
import com.example.reset.food_database.objects.Unit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by Denis Kerner
 */

//logic for editing foodentries
public class logic {

    private Activity activity;
    private database data;
    private com.example.reset.food_database.edit_diaryEntry.gui gui;
    private List<Unit> unitList = new ArrayList<Unit>();
    private DiaryEntry currentDiaryEntry;

    public logic(Activity act, database data, com.example.reset.food_database.edit_diaryEntry.gui gui) {
        super();
        this.data = data;
        this.gui = gui;
        activity = act;

        //getting the Id of the food chosen in list_food
        Intent intent = activity.getIntent();
        if (intent != null) {
            DatabaseHandler db = new DatabaseHandler(activity);
            db.getReadableDatabase();
            currentDiaryEntry = db.getDiaryEntry(intent.getIntExtra("handoverId", 0));
        }


        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy"); // TODO DatePicker
        gui.getDate_textfield().setText(dateFormat.format(currentDiaryEntry.getDate()));
        Log.d("editDiaryEntry: ", ""+currentDiaryEntry.getKcal());
        gui.getKcal_content_textview().setText(""+currentDiaryEntry.getKcal());
        gui.getName_content_textview().setText(currentDiaryEntry.getFoodname());
        gui.getPortion_textfield().setText(Double.toString(currentDiaryEntry.getPortion()));
        gui.getQuantity_content_textview().setText(Double.toString(currentDiaryEntry.getQuantity()));
        gui.getUnit_content_textview().setText(currentDiaryEntry.getUnit().getName());
    }

    //updates the diaryEntry
    public void submitFoodEditButtonClicked() {

        if (gui.getDate_textfield().getText().toString().isEmpty() || gui.getPortion_textfield().getText().toString().isEmpty() ) {
            Toast.makeText(activity, "Please fill in the whole form!", Toast.LENGTH_SHORT).show();
        } else {
            DatabaseHandler db = new DatabaseHandler(activity);
            Date date = new Date();
            SimpleDateFormat sdf= new SimpleDateFormat("dd.MM.yyyy");
            try {
                date= sdf.parse(gui.getDate_textfield().getText().toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Log.d("editDiaryEntry:", ""+date.toString());
            db.updateDiaryEntry(currentDiaryEntry.getId(),
                    currentDiaryEntry.getFoodname(),
                    currentDiaryEntry.getKcal(),
                    Double.parseDouble(gui.getPortion_textfield().getText().toString()),
                    currentDiaryEntry.getUnit(),
                    date,
                    currentDiaryEntry.getQuantity() //TODO update database handler
            );

            Toast.makeText(activity, currentDiaryEntry.getFoodname() + " has been edited!", Toast.LENGTH_SHORT).show();
            Intent myIntent = new Intent(activity, com.example.reset.food_database.diary.init.class);
            activity.startActivity(myIntent);
        }
    }


}


