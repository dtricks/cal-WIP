package com.example.reset.food_database.diary;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.reset.food_database.DatabaseHandler;
import com.example.reset.food_database.diary.database;
import com.example.reset.food_database.diary.gui;
import com.example.reset.food_database.objects.DiaryEntry;
import com.example.reset.food_database.objects.Food;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Denis Kerner
 */


//logic for adding a unit to the unit list/spinner
public class logic {

        private Activity activity;
        private database data;
        private gui gui;

        private List<DiaryEntry> diaryEntryList = new ArrayList<>();

        public logic(Activity act, database data, gui gui) {
            super();
            this.data = data;
            this.gui = gui;
            activity = act;

            Intent intent = activity.getIntent();

            fillList();

            gui.getDiary_list_view().setTextFilterEnabled(true);
            setupSearchView();

        }

    public database getData() {
        return data;
    }

    public void setData(database data) {
        this.data = data;
    }

    public Activity getActivity() {
        return activity;
    }

    public void addFoodButtonClicked() {
        Intent myIntent = new Intent(activity, com.example.reset.food_database.list_food.init.class);
        activity.startActivity(myIntent);
        Toast.makeText(activity, "addFoodButtonClicked", Toast.LENGTH_SHORT);

    }

    public void addRecipeButtonClicked() {
        Intent myIntent = new Intent(activity, com.example.reset.food_database.list_recipes.init.class);
        activity.startActivity(myIntent);
        Toast.makeText(activity, "addRecipeButtonClicked", Toast.LENGTH_SHORT);
    }

    public void setupDatabase(){
        DatabaseHandler db =new DatabaseHandler(activity);
        List<DiaryEntry> diaryEntryList=db.getDiaryEntryList();
        getData().setCurrentlyFilteredList(diaryEntryList);
        getData().setCompleteListFromDB(diaryEntryList);
    }

    //filling the list with diaryEntries from database
    private void fillList() {

        DatabaseHandler db = new DatabaseHandler(activity);
        diaryEntryList = db.getDiaryEntryList();
        List<String> diaryEntryAdapter = new ArrayList<String>();

        for (DiaryEntry object: diaryEntryList) {
            double portion = object.getPortion();
            double quantity = object.getQuantity();
            String quantityBearbeitet =  String.format(((portion % 1.0D) == 0.0D) ? "%.0f" : "%.1f", quantity);
            String unitName = object.getUnit().getName();
            String foodName = object.getFoodname();
            String kcal = Integer.toString(object.getKcal());
            SimpleDateFormat simpleDateFormat= new SimpleDateFormat("dd.MM.yyyy");
            String date = simpleDateFormat.format(object.getDate());
            diaryEntryAdapter.add(Double.toString(portion)+" Portion(en) "+quantityBearbeitet + " " + unitName + " " + foodName + " (" + kcal + " kcal)" + "\n" + date);

        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity, android.R.layout.simple_list_item_1, diaryEntryAdapter);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_2);
        gui.getDiary_list_view().setAdapter(adapter);
    }

    public void fillList(List<DiaryEntry> diaryEntryList) {

        List<String> diaryEntryAdapter = new ArrayList<String>();

        for (DiaryEntry object: diaryEntryList) {
            double portion = object.getPortion();
            double quantity = object.getQuantity();
            String quantityBearbeitet =  String.format(((portion % 1.0D) == 0.0D) ? "%.0f" : "%.1f", quantity);
            String unitName = object.getUnit().getName();
            String foodName = object.getFoodname();
            String kcal = Integer.toString(object.getKcal());
            SimpleDateFormat simpleDateFormat= new SimpleDateFormat("dd.MM.yyyy");
            String date = simpleDateFormat.format(object.getDate());
            diaryEntryAdapter.add(Double.toString(portion)+" Portion(en) "+quantityBearbeitet + " " + unitName + " " + foodName + " (" + kcal + " kcal)" + "\n" + date);

        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity, android.R.layout.simple_list_item_1, diaryEntryAdapter);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_2);
        gui.getDiary_list_view().setAdapter(adapter);
    }

    public int getKcalOfDay(Date date){
        DatabaseHandler db = new DatabaseHandler(activity);
        diaryEntryList = db.getDiaryEntryList();
        int total_kcal=0;
        for (DiaryEntry object: diaryEntryList) {
            if (date.getDay() == (object.getDate().getDay())){ //TODO check if correct
                total_kcal+=object.getKcal()*object.getPortion();
            }
        }

        return total_kcal;
    }

    public int getKcalOfCurrentlyFiltered(){
        DatabaseHandler db = new DatabaseHandler(activity);
        diaryEntryList = getData().getCurrentlyFilteredList();
        int total_kcal=0;
        for (DiaryEntry object: diaryEntryList) {
            total_kcal+=object.getKcal()*object.getPortion();
        }

        return total_kcal;
    }

    public int getKcalTotal( Activity activity){ //For temporary use
        DatabaseHandler db = new DatabaseHandler(activity);
        diaryEntryList = db.getDiaryEntryList();
        int total_kcal=0;
        for (DiaryEntry object: diaryEntryList) {
            total_kcal+=object.getKcal()*object.getPortion();
        }
        return total_kcal;
    }
    //------------------------------------
    public double calculateProgress(int sum_of_meals, int maxKcal){
        double sum_of_meals_double=sum_of_meals;
        double maxKcal_double=maxKcal;
        return sum_of_meals_double/maxKcal_double*100;
    }

    public int calculateProgress(Activity activity){
        //double sum_of_meals_double=getKcalTotal(activity);
        double sum_of_meals_double=getKcalOfCurrentlyFiltered();
        double maxKcal_double=getMaxKcalFromSettings(activity);
        return (int) (sum_of_meals_double/maxKcal_double*100);
    }

    public void setProgressBar(Activity activity){
        //gui.getProgress_bar().setMax(getMaxKcalFromSettings(activity));
        gui.getProgress_bar().setMax(100);
        //gui.getProgress_bar().setMin(0);
        gui.getProgress_bar().setProgress(calculateProgress(activity));
    }

    public int getMaxKcalFromSettings(Activity activity){
        //get max kcal data from Settings
        DatabaseHandler db = new DatabaseHandler(activity);
        int maxKcal=db.getSettings();

        return maxKcal;
    }
    public void setTextViewContents(Activity activity) {

        int maxKcal=getMaxKcalFromSettings(activity);
        //get sum of kcal in diary list
        //int sum = getKcalTotal(activity);
        int sum = getKcalOfCurrentlyFiltered();
        //concatenate string
        String concatenatedString = new StringBuilder().append(sum).append("/").append(maxKcal).append("    ~").append(calculateProgress(activity)).append("%").toString();
        //put into Textview
        gui.getProgressBar_textview().setText(concatenatedString);
    }

    public void setTextViewContents(Activity activity, int sum_of_meals) {

        int maxKcal=getMaxKcalFromSettings(activity);
        //get sum of kcal in diary list
        int sum = getKcalOfCurrentlyFiltered();
        //concatenate string
        String concatenatedString = new StringBuilder().append(sum).append("/").append(maxKcal).append("    ~").append(calculateProgress(activity)).append("%").toString();
        //put into Textview
        gui.getProgressBar_textview().setText(concatenatedString);
    }
    //------------------------------------

    public void itemClicked(String selectedItem){

        int counter = 0;

        for(int i=0; i < diaryEntryList.size(); i++)
        {
            if(diaryEntryList.get(i).equals(selectedItem))
            {
                counter = i;
            }
        }

        final int selectedItemId = counter;


        //opens Dialog with the 2 choices edit diaryEntry, delete diaryEntry
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("What would You like to do with your choosen diary entry?");


        //leads user to editdiaryEntry + handover the chosen diaryEntry id
        builder.setNeutralButton("Edit",
                new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        Intent alertIntent = new Intent(activity, com.example.reset.food_database.edit_diaryEntry.init.class);
                        gui.getDiary_list_view().getSelectedItemId();
                        alertIntent.putExtra("handoverId", diaryEntryList.get(selectedItemId).getId());
                        activity.startActivity(alertIntent);
                    }
                });

        //deletes the clicked food
        builder.setNegativeButton("Delete",
                new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        int diaryEntryId = diaryEntryList.get(selectedItemId).getId();


                        DatabaseHandler db = new DatabaseHandler(activity);

                        if(db.deleteDiaryEntry(diaryEntryId)){
                            Toast.makeText(activity, diaryEntryList.get(selectedItemId).getFoodname() + " has been successfully deleted!", Toast.LENGTH_SHORT).show();
                            fillList();
                        }
                        else{
                            Toast.makeText(activity, "Deleting was NOT successful!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        builder.create().show();

    }

    //sets up the search
    private void setupSearchView() {
        gui.getDate_filter().setIconifiedByDefault(false);
        gui.getDate_filter().setSubmitButtonEnabled(true);
        gui.getDate_filter().setQueryHint("Search Here");

    }
    //submit unit to unitlist/spinner
        /*public void submitFoodButtonClicked() {

        String givenDate =  gui.getDate_textfield().getText().toString();
        Double givenPortion =  Double.parseDouble(gui.getPortion_textfield().getText().toString());
        DatabaseHandler db = new DatabaseHandler(activity);

        if (givenDate.matches("")){
            Toast.makeText(activity, "Please insert a Date!", Toast.LENGTH_SHORT).show();

        }
        if (givenPortion.isNaN() || givenPortion.equals(0)){
            Toast.makeText(activity, "Please insert a valid Portion!", Toast.LENGTH_SHORT).show();

        }
        else {
            //db.insertDiaryEntry(db.getWritableDatabase(), givenDate, givenPortion);

            Toast.makeText(activity, "Diary Entry for" + gui.getName_content_textview().getText().toString() + " has been added!", Toast.LENGTH_SHORT).show();
            *//*Intent intent = activity.getIntent();
            Intent myIntent = new Intent(activity, com.example.reset.food_database.add_food.init.class);
            if (intent != null) {
                myIntent.putExtra("name", intent.getStringExtra("name"));
                myIntent.putExtra("quantity", intent.getStringExtra("quantity"));
                myIntent.putExtra("kcal", intent.getStringExtra("kcal"));
                myIntent.putExtra("date", givenDate);
                myIntent.putExtra("portion", givenPortion);
            }
            activity.startActivity(myIntent);*//*
        }
    }

    public void dateFieldClicked() {
        gui.getDate_textfield().setText("01.01.2018");
    }*/


}


