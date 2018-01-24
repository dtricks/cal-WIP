package com.example.reset.food_database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHandler extends SQLiteOpenHelper{


    //Datenbanktabellen für Einheiten
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "FoodDiary";
    private static final String TABLE_NAME = "Unit";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME1 = "Unit";

    //Datenbanktabelle für Lebensmittel
    private static final String TABLE2_NAME = "Food";
    private static final String TABLE2_ID = "id";
    private static final String TABLE2_COLUMN_NAME = "foodname";
    private static final String TABLE2_COLUMN_KCAL = "kcal";
    private static final String TABLE2_COLUMN_QUANTITY = "quantity";
    private static final String TABLE2_COLUMN_UNIT = "unit";

    //Datenbanktabelle für das Tagebuch
    private static final String TABLE3_NAME = "Diary";
    private static final String TABLE3_ID = "id";
    private static final String TABLE3_COLUMN_FOOD = "foodname";
    private static final String TABLE3_COLUMN_KCAL = "kcal";
    private static final String TABLE3_COLUMN_DATE = "date";

    public DatabaseHandler(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Creating Tables

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_ITEM_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_NAME1 + " TEXT)";
        db.execSQL(CREATE_ITEM_TABLE);

        String CREATE_PHASES_TABLE2 = "CREATE TABLE " + TABLE2_NAME + "("
                + TABLE2_ID + " INTEGER PRIMARY KEY," + TABLE2_COLUMN_NAME
                + " TEXT," + TABLE2_COLUMN_KCAL + " INTEGER," + TABLE2_COLUMN_QUANTITY
                + " INTEGER," + TABLE2_COLUMN_UNIT + " TEXT)";
        db.execSQL(CREATE_PHASES_TABLE2);

        insertUnit(db, "g");
        insertUnit(db, "EL");
        insertUnit(db,"TL");
        insertUnit(db, "Stueck");
        insertUnit(db, "ml");
        insertUnit(db, "Portion");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE2_NAME);

        onCreate(db);
    }

    public void insertUnit(SQLiteDatabase db, String unit){
        String insertSQL = "INSERT INTO " + TABLE_NAME + " ("+COLUMN_NAME1+") VALUES ('"+unit+"')";
        db.execSQL(insertSQL);
    }

    public void insertFood(String name, int kcal, int quantity, String unit) {
        SQLiteDatabase db = this.getReadableDatabase();

        String insertSQL = "INSERT INTO " + TABLE2_NAME + " (" + TABLE2_COLUMN_NAME
                + ", " + TABLE2_COLUMN_KCAL + ", " + TABLE2_COLUMN_QUANTITY + ", " + TABLE2_COLUMN_UNIT + ") " +
                "VALUES ('" + name + "', " + kcal + ", " + quantity + ", '" + unit + "')";

            db.execSQL(insertSQL);
        }

    public List<String> getUnits() {
        List<String> list = new ArrayList<String>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
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
    public List<String> getFood() {
        List<String> list = new ArrayList<String>();
        String selectQuery = "SELECT * FROM " + TABLE2_NAME;
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

        String selectQuery = "SELECT * FROM " + TABLE_NAME;
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
            String deleteQuery = "DELETE FROM " + TABLE_NAME + " WHERE "
                    + COLUMN_NAME1 + " = " + "'" + unit + "'";
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
        String deleteQuery = "DELETE FROM " + TABLE2_NAME + " WHERE " +
                 TABLE2_COLUMN_NAME + " = " + "'" + name + "' AND " +
                 TABLE2_COLUMN_KCAL + " = " + "'" + kcal + "' AND " +
                 TABLE2_COLUMN_QUANTITY + " = " + "'" + quantity + "' AND " +
                 TABLE2_COLUMN_UNIT + " = " + "'" + unit + "'";
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


