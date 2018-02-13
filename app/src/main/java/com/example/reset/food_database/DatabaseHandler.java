package com.example.reset.food_database;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.reset.food_database.objects.DiaryEntry;
import com.example.reset.food_database.objects.Food;
import com.example.reset.food_database.objects.Settings;
import com.example.reset.food_database.objects.Unit;

import static android.content.ContentValues.TAG;

/**
 * Created by Oliver Gras
 */

public class DatabaseHandler extends SQLiteOpenHelper{


    //creating database
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "FoodDiary";

    //databasetable for  Units
    private static final String UNIT_NAME = "Unit";
    private static final String UNIT_COLUMN_ID = "id";
    private static final String UNIT_COLUMN_NAME = "Unit";

    //databasetable for  Food
    private static final String FOOD_NAME = "Food";
    private static final String FOOD_ID = "id";
    private static final String FOOD_COLUMN_NAME = "foodname";
    private static final String FOOD_COLUMN_KCAL = "kcal";
    private static final String FOOD_COLUMN_QUANTITY = "quantity";
    private static final String FOOD_COLUMN_UNIT = "unit";

    //databasetable for  Diary
    private static final String DIARYENTRY_NAME = "Diary";
    private static final String DIARYENTRY_ID = "id";
    private static final String DIARYENTRY_COLUMN_FOOD = "foodname";
    private static final String DIARYENTRY_COLUMN_KCAL = "kcal";
    private static final String DIARYENTRY_COLUMN_PORTION = "portion";
    private static final String DIARYENTRY_COLUMN_DATE = "date";
    private static final String DIARYENTRY_COLUMN_UNIT = "unit";
    private static final String DIARYENTRY_COLUMN_QUANTITY = "quantity";

    //databasetable for  Recipe
    private static final String RECIPE_NAME = "Recipes";
    private static final String RECIPE_ID = "id";
    private static final String RECIPE_COLUMN_RECIPE = "recipename";
    private static final String RECIPE_COLUMN_KCAL = "recipekcal";

    //databasetable for  Recipeingredients
    private static final String RECIPEINGREDIENTS_NAME = "Recipeingredients";
    private static final String RECIPEINGREDIENTS_ID = "id";
    private static final String RECIPEINGREDIENTS_RECIPEID = "recipeid";
    private static final String RECIPEINGREDIENTS_FOODID = "foodid";
    private static final String RECIPEINGREDIENTS_COLUMN_QUANTITY = "quantity";

    //databasetable for Settings
    private static final String SETTINGS_NAME = "SETTINGS";
    private static final String SETTINGS_ID = "id";
    private static final String SETTINGS_MAXKCAL = "maxkcal";

    //

    public DatabaseHandler(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Creating Tables

    @Override
    public void onCreate(SQLiteDatabase db){
        //create Table unit
        String CREATE_UNIT = "CREATE TABLE " + UNIT_NAME + "(" + UNIT_COLUMN_ID + " INTEGER PRIMARY KEY," + UNIT_COLUMN_NAME + " TEXT)";
        db.execSQL(CREATE_UNIT);

        //create Table food
        String CREATE_FOOD_TABLE = "CREATE TABLE " + FOOD_NAME + "("
                + FOOD_ID + " INTEGER PRIMARY KEY," + FOOD_COLUMN_NAME
                + " TEXT," + FOOD_COLUMN_KCAL + " INTEGER," + FOOD_COLUMN_QUANTITY
                + " DOUBLE," + FOOD_COLUMN_UNIT + " TEXT)";
        db.execSQL(CREATE_FOOD_TABLE);

        //create Table diaryEntry
        String CREATE_DIARYENTRY_TABLE = "CREATE TABLE " + DIARYENTRY_NAME + "("
                + DIARYENTRY_ID + " INTEGER PRIMARY KEY," + DIARYENTRY_COLUMN_FOOD + " TEXT,"
                + DIARYENTRY_COLUMN_KCAL + " INTEGER," + DIARYENTRY_COLUMN_PORTION + " DOUBLE,"
                + DIARYENTRY_COLUMN_DATE + " DATE," + DIARYENTRY_COLUMN_UNIT + " TEXT,"
                + DIARYENTRY_COLUMN_QUANTITY + " DOUBLE)";
        db.execSQL(CREATE_DIARYENTRY_TABLE);

        //create Table recipe
        String CREATE_RECIPE_TABLE = "CREATE TABLE " + RECIPE_NAME + "("
                + RECIPE_ID + " INTEGER PRIMARY KEY," + RECIPE_COLUMN_RECIPE + " TEXT,"
                + RECIPE_COLUMN_KCAL + " INTEGER)";
        db.execSQL(CREATE_RECIPE_TABLE);

        //create Table recipeingredients
        String CREATE_RECIPEINGREDIENTS_TABLE = "CREATE TABLE " + RECIPEINGREDIENTS_NAME + "("
                + RECIPEINGREDIENTS_ID + " INTEGER PRIMARY KEY,"  + RECIPEINGREDIENTS_RECIPEID + " INTEGER,"
                + RECIPEINGREDIENTS_FOODID + " INTEGER,"+ RECIPEINGREDIENTS_COLUMN_QUANTITY+ " DOUBLE)";
        db.execSQL(CREATE_RECIPEINGREDIENTS_TABLE);

        //create Table settings
        String CREATE_SETTINGS_TABLE = "CREATE TABLE " + SETTINGS_NAME + "("
                + SETTINGS_ID + " INTEGER PRIMARY KEY," + SETTINGS_MAXKCAL + " INTEGER)";
        db.execSQL(CREATE_SETTINGS_TABLE);

        //Insert default units
        insertUnit(db, "g");
        insertUnit(db, "EL");
        insertUnit(db,"TL");
        insertUnit(db, "Stueck");
        insertUnit(db, "ml");
        insertUnit(db, "Portion");
        insertSettings(db, 2000);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

        db.execSQL("DROP TABLE IF EXISTS " + UNIT_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + FOOD_NAME);

        onCreate(db);
    }

    public void insertUnit(SQLiteDatabase db, String unit){
        String insertSQL = "INSERT INTO " + UNIT_NAME + " ("+UNIT_COLUMN_NAME+") VALUES ('"+unit+"')";
        db.execSQL(insertSQL);
    }

    public void insertSettings(SQLiteDatabase db, int dailies){
        String insertSQL = "INSERT INTO " + SETTINGS_NAME + " ("+SETTINGS_MAXKCAL+") VALUES ('"+dailies+"')";
        db.execSQL(insertSQL);
    }

    public void insertFood(String name, int kcal, double quantity, int unit) {
        SQLiteDatabase db = this.getReadableDatabase();

        String insertSQL = "INSERT INTO " + FOOD_NAME + " (" + FOOD_COLUMN_NAME
                + ", " + FOOD_COLUMN_KCAL + ", " + FOOD_COLUMN_QUANTITY + ", " + FOOD_COLUMN_UNIT + ") " +
                "VALUES ('" + name + "', " + kcal + ", " + quantity + ", '" + unit + "')";

        db.execSQL(insertSQL);
    }

    public void insertDiaryEntry ( String foodName, int kcal, double portion, Unit unit, Date date, double quantity) {
        SQLiteDatabase db = this.getReadableDatabase();
        String insertSQL  = "INSERT INTO " + DIARYENTRY_NAME + " ("
                + DIARYENTRY_COLUMN_FOOD + ", "
                + DIARYENTRY_COLUMN_KCAL + ", "
                + DIARYENTRY_COLUMN_PORTION + ", "
                + DIARYENTRY_COLUMN_DATE + ", "
                + DIARYENTRY_COLUMN_UNIT + ", "
                + DIARYENTRY_COLUMN_QUANTITY + ") "
                +"VALUES ('"
                + foodName + "', '"
                + Integer.toString(kcal) + "', '"
                + Double.toString(portion) + "', '"
                + date.toString() + "', '" //TODO could be dangerous with the locale and date formatting
                + unit.getName() + "', '"
                + Double.toString(quantity) + "') ";
        db.execSQL(insertSQL);
    }

    //returns a Unit names as a list of Strings
    public List<String> getUnits() {
        List<String> list = new ArrayList<String>();
        String selectQuery = "SELECT * FROM " + UNIT_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            do{
                list.add(cursor.getString(1));
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }


    //returns all Units as a list
    public List<Unit> getUnits_new() {
        List<Unit> list = new ArrayList<Unit>();
        String selectQuery = "SELECT * FROM " + UNIT_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            do{
                Unit unit = new Unit();
                unit.setId(cursor.getInt(0));
                unit.setName(cursor.getString(1));
                list.add(unit);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }


    public Food getFood_new(int id) {
        Food food=new Food();
        String selectQuery = "SELECT * FROM " + FOOD_NAME + " WHERE " + FOOD_ID + " = " + "'" + id + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do{
                food.setId(cursor.getInt(0));
                food.setName(cursor.getString(1));
                food.setKcal(cursor.getInt(2));
                food.setQuantity(cursor.getDouble(3));
                food.setUnit(getUnit_new(cursor.getInt(4)));
                //list.add(cursor.getString(3) + " " + cursor.getString(4) + " " + cursor.getString(1) + " (" + cursor.getString(2) + " kcal)");
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return food;
    }

    public DiaryEntry getDiaryEntry_old(int id) {
        DiaryEntry diaryEntry=new DiaryEntry();
        String selectQuery = "SELECT * FROM " + FOOD_NAME + " WHERE " + FOOD_ID + " = " + "'" + id + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst() && !cursor.isNull(1)) {
            do{
                diaryEntry.setId(cursor.getInt(0));
                diaryEntry.setFoodname(cursor.getString(1));
                diaryEntry.setKcal(cursor.getInt(2));
                diaryEntry.setPortion(cursor.getDouble(3));
                Date date =new Date();
                SimpleDateFormat sdf = new SimpleDateFormat();
                try {
                    date = sdf.parse(cursor.getString(4));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                finally {
                    cursor.getString(4);
                }
                diaryEntry.setDate(date);
                Log.d(TAG, "getDiaryEntry: " +date.toString());
                Log.d(TAG, "getDiaryEntry: " +cursor.getString(5));
                diaryEntry.setUnit(new Unit(cursor.getInt(0), cursor.getString(5)));
                diaryEntry.setQuantity(cursor.getDouble(6));

            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return diaryEntry;
    }

    public DiaryEntry getDiaryEntry(int id) {
        List<DiaryEntry> diaryEntryList=getDiaryEntryList();
        DiaryEntry diaryEntry=new DiaryEntry();
        for (DiaryEntry diaryEntryFromList: diaryEntryList
             ) {
            if (diaryEntryFromList.getId()==id)
                diaryEntry=diaryEntryFromList;
        }
        return diaryEntry;
    }

    public int getSettings() {
        int settingsMaxKcal;
        String selectQuery = "SELECT * FROM " + SETTINGS_NAME + " WHERE " + SETTINGS_ID + " = " + "'" + 1 + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        settingsMaxKcal = cursor.getInt(1);
        cursor.close();
        db.close();
        return settingsMaxKcal;
    }


    public Unit getUnit_new(int id) {
        Unit unit = new Unit();
        String selectQuery = "SELECT * FROM " + UNIT_NAME + " WHERE " + UNIT_COLUMN_ID + " = " + "'" + id + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do{
                //food.setId(cursor.getInt(0));
                unit.setId(cursor.getInt(0));
                unit.setName(cursor.getString(1));
            }while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return unit;
    }

    public List<String> getFood() {
        List<String> list = new ArrayList<String>();
        String selectQuery = "SELECT * FROM " + FOOD_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            do{

                list.add(cursor.getString(3) + " " + cursor.getString(4) + " " + cursor.getString(1) + " (" + cursor.getString(2) + " kcal)");
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }



    public List<Food> getFoodList() {
        List<Food> list = new ArrayList<Food>();
        String selectQuery = "SELECT * FROM " + FOOD_NAME + " ORDER BY " + FOOD_COLUMN_NAME + " ASC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                Food food = new Food();
                food.setId(cursor.getInt(0));
                food.setName(cursor.getString(1));
                food.setKcal(cursor.getInt(2));
                food.setQuantity(cursor.getDouble(3));
                food.setUnit(getUnit_new(cursor.getInt(4)));
                list.add(food);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }

    public List<DiaryEntry> getDiaryEntryList() {
        List<DiaryEntry> list = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + DIARYENTRY_NAME + " ORDER BY " + DIARYENTRY_COLUMN_FOOD + " ASC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                DiaryEntry diaryEntry = new DiaryEntry();
                diaryEntry.setId(cursor.getInt(0));
                diaryEntry.setFoodname(cursor.getString(1));
                diaryEntry.setKcal(cursor.getInt(2));
                diaryEntry.setPortion(cursor.getDouble(3));
                diaryEntry.setDate(new Date(cursor.getString(4)));
                //diaryEntry.setUnit(getUnit_new(cursor.getInt(0)));
                diaryEntry.setUnit(new Unit(cursor.getInt(0), cursor.getString(5)));
                diaryEntry.setQuantity(cursor.getDouble(6));
                list.add(diaryEntry);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;

    }




    public boolean doesUnitExist(String unit) {

        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + UNIT_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                if (cursor.getString(1).matches(unit)) {
                    return true;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return false; //die unit existiert noch nicht
    }

    // Deletes category from database
    public boolean deleteUnit(String unit) {

        try{
            String deleteQuery = "DELETE FROM " + UNIT_NAME + " WHERE "
                    + UNIT_COLUMN_NAME + " = " + "'" + unit + "'";
            SQLiteDatabase db = this.getReadableDatabase();
            db.execSQL(deleteQuery);
            db.close();
            return true;
        }
        catch(SQLException e)
        {
            return false;
        }

    }


    public boolean deleteFood(int foodID){

        try{
            String deleteQuery = "DELETE FROM " + FOOD_NAME + " WHERE " +
                    FOOD_ID + " = " + "'" + foodID + "'";
            SQLiteDatabase db = this.getReadableDatabase();
            db.execSQL(deleteQuery);
            db.close();
            return true;
        }
        catch(SQLException e)
        {
            return false;
        }


    }

    public boolean deleteDiaryEntry(int diaryEntryId){
        try{
            String deleteQuery = "DELETE FROM " + DIARYENTRY_NAME + " WHERE " +
                    DIARYENTRY_ID + " = " + "'" + diaryEntryId + "'";
            SQLiteDatabase db = this.getReadableDatabase();
            db.execSQL(deleteQuery);
            db.close();
            return true;
        }
        catch(SQLException e)
        {
            return false;
        }
    }

    public void updateFood (int Id, String name, int kcal, double quantity, int unit) {
        SQLiteDatabase db = this.getReadableDatabase();
        String insertSQL  = "UPDATE " + FOOD_NAME +  " SET " +  FOOD_COLUMN_NAME + " = '" + name + "', " + FOOD_COLUMN_KCAL + " = " + kcal + ", "
                + FOOD_COLUMN_QUANTITY + " = " + quantity + ", "  + FOOD_COLUMN_UNIT + " = " + unit
                + " WHERE " + FOOD_ID + "=" + Id;
        db.execSQL(insertSQL);
    }

    public void updateSettings (int maxkcal) {
        SQLiteDatabase db = this.getReadableDatabase();
        String insertSQL  = "UPDATE " + SETTINGS_NAME +  " SET " +  SETTINGS_MAXKCAL + " = " + maxkcal
                + " WHERE " + SETTINGS_ID + "=" + 1;
        db.execSQL(insertSQL);
    }

    public void updateUnit (int unitId, String unitName) {
        SQLiteDatabase db = this.getReadableDatabase();
        String insertSQL  = "UPDATE " + UNIT_NAME +  " SET " +  UNIT_COLUMN_NAME + " = " + unitName
                + " WHERE " + SETTINGS_ID + "=" + unitId;
        db.execSQL(insertSQL);
    }

    public void updateDiaryEntry (int diaryEntryId, String foodName, int kcal, double portion, Unit unit, Date date, double quantity) {
        SQLiteDatabase db = this.getReadableDatabase();
        String insertSQL  = "UPDATE " + DIARYENTRY_NAME + " SET "
                 + DIARYENTRY_COLUMN_FOOD + " = " + "'" +foodName +"'" +", "
                + DIARYENTRY_COLUMN_KCAL + " = " + "'" +Integer.toString(kcal) +"'" +", "
                + DIARYENTRY_COLUMN_PORTION + " = " + "'" +Double.toString(portion) +"'" +", "
                + DIARYENTRY_COLUMN_DATE + " = " + "'" +date.toString()+"'" +", "//TODO add locale so that date formatting doesnt change
                + DIARYENTRY_COLUMN_UNIT + " = " + "'" +unit.getName()+"'" +", "
                + DIARYENTRY_COLUMN_QUANTITY + " = " + "'" +Double.toString(quantity)+"'" +""
                +" WHERE "+ DIARYENTRY_ID + "="  +Integer.toString(diaryEntryId);
        db.execSQL(insertSQL);
    }






}


