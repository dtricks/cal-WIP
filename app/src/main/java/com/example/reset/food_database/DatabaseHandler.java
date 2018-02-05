package com.example.reset.food_database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.reset.food_database.objects.Food;
import com.example.reset.food_database.objects.Unit;


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
                + DIARYENTRY_COLUMN_DATE + " DATE," + DIARYENTRY_COLUMN_UNIT + " TEXT)";
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
                + SETTINGS_ID + " INTEGER PRIMARY KEY" + SETTINGS_MAXKCAL + " INTEGER)";
        db.execSQL(CREATE_SETTINGS_TABLE);

        //Insert default units
        insertUnit(db, "g");
        insertUnit(db, "EL");
        insertUnit(db,"TL");
        insertUnit(db, "Stueck");
        insertUnit(db, "ml");
        insertUnit(db, "Portion");
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

    public void insertFood(String name, int kcal, int quantity, String unit) {
        SQLiteDatabase db = this.getReadableDatabase();

        String insertSQL = "INSERT INTO " + FOOD_NAME + " (" + FOOD_COLUMN_NAME
                + ", " + FOOD_COLUMN_KCAL + ", " + FOOD_COLUMN_QUANTITY + ", " + FOOD_COLUMN_UNIT + ") " +
                "VALUES ('" + name + "', " + kcal + ", " + quantity + ", '" + unit + "')";

            db.execSQL(insertSQL);
        }

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

    public Food getFood_new(int id) {
        Food food=new Food();
        String selectQuery = "SELECT * FROM " + FOOD_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.getColumnIndex(FOOD_ID)==id){
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


    public Unit getUnit_new(int id) {
        Unit unit = new Unit();
        String selectQuery = "SELECT * FROM " + FOOD_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.getColumnIndex(UNIT_COLUMN_ID)==id){
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
        String selectQuery = "SELECT * FROM " + FOOD_NAME;
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


    public boolean deleteFood(String name, String kcal, String quantity, String unit){

        try{
        String deleteQuery = "DELETE FROM " + FOOD_NAME + " WHERE " +
                 FOOD_COLUMN_NAME + " = " + "'" + name + "' AND " +
                 FOOD_COLUMN_KCAL + " = " + "'" + kcal + "' AND " +
                 FOOD_COLUMN_QUANTITY + " = " + "'" + quantity + "' AND " +
                 FOOD_COLUMN_UNIT + " = " + "'" + unit + "'";
        SQLiteDatabase db = this.getReadableDatabase();
            db.execSQL(deleteQuery);
            db.close();
            return true; //generic comment to test GitHub
        }
        catch(SQLException e)
        {
            return false;
        }


    }


    }


