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


    //Datenbankerstellung
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "FoodDiary";

    //Datenbanktabellen für Einheiten
    private static final String UNIT_NAME = "Unit";
    private static final String UNIT_COLUMN_ID = "id";
    private static final String UNIT_COLUMN_NAME = "Unit";

    //Datenbanktabelle für Food
    private static final String FOOD_NAME = "Food";
    private static final String FOOD_ID = "id";
    private static final String FOOD_COLUMN_NAME = "foodname";
    private static final String FOOD_COLUMN_KCAL = "kcal";
    private static final String FOOD_COLUMN_QUANTITY = "quantity";
    private static final String FOOD_COLUMN_UNIT = "unit";

    //Datenbanktabelle für das Tagebuch
    private static final String TABLE3_NAME = "Diary";
    private static final String TABLE3_ID = "id";
    private static final String TABLE3_COLUMN_FOOD = "foodname";
    private static final String TABLE3_COLUMN_KCAL = "kcal";
    private static final String TABLE3_COLUMN_PORTION = "portion";
    private static final String TABLE3_COLUMN_DATE = "date";
    private static final String TABLE3_COLUMN_FOODORRECIPE = "isRecipe"; //true=food, false=recipe

    //Datenbanktabelle für Rezepte
    private static final String TABLE4_NAME = "Recipes";
    private static final String TABLE4_ID = "id";
    private static final String TABLE4_COLUMN_RECIPE = "recipename";
    private static final String TABLE4_COLUMN_KCAL = "recipekcal";

    //Datenbanktabelle Rezeptinhalte
    private static final String TABLE5_NAME = "Recipeingredients";
    private static final String TABLE5_ID = "id";
    private static final String TABLE5_RECIPEID = "recipeid";
    private static final String TABLE5_FOODID = "foodid";
    private static final String TABLE5_COLUMN_FOOD = "food";
    private static final String TABLE5_COLUMN_FOODKCAL = "foodkcal";
    private static final String TABLE5_COLUMN_QUANTITY = "quantity";

    //Datenbanktabelle für Settings
    private static final String TABLE6_NAME = "SETTINGS";
    private static final String TABLE6_ID = "id";
    private static final String TABLE6_MAXKCAL = "maxkcal";

    //

    public DatabaseHandler(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Creating Tables

    @Override
    public void onCreate(SQLiteDatabase db){
        //create Table unit
        String CREATE_ITEM_TABLE = "CREATE TABLE " + UNIT_NAME + "(" + UNIT_COLUMN_ID + " INTEGER PRIMARY KEY," + UNIT_COLUMN_NAME + " TEXT)";
        db.execSQL(CREATE_ITEM_TABLE);

        //create Table food
        String CREATE_PHASES_TABLE2 = "CREATE TABLE " + FOOD_NAME + "("
                + FOOD_ID + " INTEGER PRIMARY KEY," + FOOD_COLUMN_NAME
                + " TEXT," + FOOD_COLUMN_KCAL + " INTEGER," + FOOD_COLUMN_QUANTITY
                + " INTEGER," + FOOD_COLUMN_UNIT + " TEXT)"; //TODO Oli double quantity
        db.execSQL(CREATE_PHASES_TABLE2);




        //create Table diaryEntry
        //TODO Oli
        //create Table diary
        //create Table settings

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
                //food.setId(cursor.getInt(0));
                food.setName(cursor.getString(1));
                food.setKcal(cursor.getInt(2));
                food.setQuantity(cursor.getDouble(3));
                Unit unit= new Unit(cursor.getString(4));
                food.setUnit(unit);
                //list.add(cursor.getString(3) + " " + cursor.getString(4) + " " + cursor.getString(1) + " (" + cursor.getString(2) + " kcal)");
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return food;
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


