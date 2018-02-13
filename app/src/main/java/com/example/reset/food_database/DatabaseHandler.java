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
import com.example.reset.food_database.objects.RecipeIngredient;
import com.example.reset.food_database.objects.Unit;
import com.example.reset.food_database.objects.Recipes;

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

    //databasetable for  Recipe
    private static final String RECIPE_NAME = "Recipes";
    private static final String RECIPE_ID = "id";
    private static final String RECIPE_COLUMN_RECIPE = "recipename";
    private static final String RECIPE_COLUMN_KCAL = "recipekcal";

    //databasetable for  Recipeingredients
    private static final String RECIPEINGREDIENTS_NAME = "Recipeingredients";
    private static final String RECIPEINGREDIENTS_ID = "id";
    private static final String RECIPEINGREDIENTS_RECIPEID = "recipeid";
    private static final String RECIPEINGREDIENTS_FOODNAME = "foodname";
    private static final String RECIPEINGREDIENTS_UNIT = "unit";
    private static final String RECIPEINGREDIENTS_KCAL = "kcal";
    private static final String RECIPEINGREDIENTS_COLUMN_QUANTITY = "quantity";
    private static final String RECIPEINGREDIENTS_PORTION = "portion";

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
                + RECIPEINGREDIENTS_FOODNAME + " TEXT,"+ RECIPEINGREDIENTS_KCAL + " INTEGER," + RECIPEINGREDIENTS_UNIT + " TEXT," + RECIPEINGREDIENTS_PORTION + " DOUBLE,"+ RECIPEINGREDIENTS_COLUMN_QUANTITY+ " DOUBLE)";
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

    //creates new unit in database
    public void insertUnit(SQLiteDatabase db, String unit){
        String insertSQL = "INSERT INTO " + UNIT_NAME + " ("+UNIT_COLUMN_NAME+") VALUES ('"+unit+"')";
        db.execSQL(insertSQL);
    }

    //creates the new daily kalories goal
    public void insertSettings(SQLiteDatabase db, int dailies){
        String insertSQL = "INSERT INTO " + SETTINGS_NAME + " ("+SETTINGS_MAXKCAL+") VALUES ('"+dailies+"')";
        db.execSQL(insertSQL);
    }

    //creates a new food entry
    public void insertFood(String name, int kcal, double quantity, String unit) {
        SQLiteDatabase db = this.getReadableDatabase();

        String insertSQL = "INSERT INTO " + FOOD_NAME + " (" + FOOD_COLUMN_NAME
                + ", " + FOOD_COLUMN_KCAL + ", " + FOOD_COLUMN_QUANTITY + ", " + FOOD_COLUMN_UNIT + ") " +
                "VALUES ('" + name + "', " + kcal + ", " + quantity + ", '" + unit + "')";

        db.execSQL(insertSQL);
    }

    public void insertRecipe(String name, int kcal) {
        SQLiteDatabase db = this.getReadableDatabase();

        String insertSQL = "INSERT INTO " + RECIPE_NAME + " (" + RECIPE_COLUMN_RECIPE
                + ", " + RECIPE_COLUMN_KCAL + ") " +
                "VALUES ('" + name + "', " + kcal + ")";

        db.execSQL(insertSQL);
    }

    //creates a new food entry
    public void insertRecipeingredient(int recipeId, String name, int kcal, double quantity, String unit, double portion) {
        SQLiteDatabase db = this.getReadableDatabase();

        String insertSQL = "INSERT INTO " + RECIPEINGREDIENTS_NAME + " (" + RECIPEINGREDIENTS_RECIPEID + ", " + RECIPEINGREDIENTS_FOODNAME + ", " + RECIPEINGREDIENTS_UNIT
                + ", " + RECIPEINGREDIENTS_COLUMN_QUANTITY + ", " + RECIPEINGREDIENTS_KCAL + ", "  + RECIPEINGREDIENTS_PORTION + ") " +
                "VALUES (" + recipeId + ", '" + name + "', '" + unit + "', " + quantity + ", " + kcal + ", " + portion + ")";

        Log.d("database", "kcal: "+ kcal);
        db.execSQL(insertSQL);
        Log.d("database", "kcal: "+ kcal);
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

    //gets food for foodlist listview
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
                food.setUnit(cursor.getString(4));
                //list.add(cursor.getString(3) + " " + cursor.getString(4) + " " + cursor.getString(1) + " (" + cursor.getString(2) + " kcal)");
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return food;
    }

    //get new Recipe (some valus are missing)
    public Recipes getRecipe_new(int id) {
        Recipes recipe = new Recipes(id,"",0);
        String selectQuery = "SELECT * FROM " + RECIPE_NAME + " WHERE " + RECIPE_ID + " = " + "'" + id + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do{
                recipe.setId(cursor.getInt(0));
                recipe.setName(cursor.getString(1));
                recipe.setKcal(cursor.getInt(2));
                //recipe.setQuantity(cursor.getDouble(3));
                //recipe.setUnit(getUnit_new(cursor.getInt(4)));
                //list.add(cursor.getString(3) + " " + cursor.getString(4) + " " + cursor.getString(1) + " (" + cursor.getString(2) + " kcal)");
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return recipe;
    }

    //gets settings for the settings
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


    //gets unit
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

    //gets unit
    public Unit getUnitbyName(String name) {
        Unit unit = new Unit();
        String selectQuery = "SELECT * FROM " + UNIT_NAME + " WHERE " + UNIT_COLUMN_NAME + " = " + "'" + name + "'";
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

    //gets food for list
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



    //gets foodlist object for list
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
                food.setUnit(cursor.getString(4));
                list.add(food);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }

    //selects all recipes
    public List<Recipes> getRecipeList() {
        List<Recipes> list = new ArrayList<Recipes>();
        String selectQuery = "SELECT * FROM " + RECIPE_NAME + " ORDER BY " + RECIPE_COLUMN_RECIPE + " ASC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                Recipes recipes = new Recipes(1,"tmp",3);
                recipes.setId(cursor.getInt(0));
                recipes.setName(cursor.getString(1));
                recipes.setKcal(cursor.getInt(2));
                list.add(recipes);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }

    // change to show inredients hier fehler beheben!
    public List<RecipeIngredient> getIngredientList(int recipeID) {
        List<RecipeIngredient> list = new ArrayList<RecipeIngredient>();
        String selectQuery = "SELECT * FROM " + RECIPEINGREDIENTS_NAME + " where " + RECIPEINGREDIENTS_RECIPEID +" = " + recipeID + " ORDER BY " + RECIPEINGREDIENTS_FOODNAME + " ASC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                RecipeIngredient recipesingredient = new RecipeIngredient();
                recipesingredient.setId(cursor.getInt(0));
                recipesingredient.setRecipeId(cursor.getInt(1));
                recipesingredient.setFoodname(cursor.getString(2));
                recipesingredient.setKcal(cursor.getInt(3));
                recipesingredient.setUnit(cursor.getString(4));
                recipesingredient.setPortion(cursor.getDouble(5));
                recipesingredient.setQuantity(cursor.getInt(6));

                //diaryEntry.setUnit(new Unit(cursor.getInt(0), cursor.getString(5)));

                list.add(recipesingredient);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }


    //checks if the unit is allready created
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

    // Deletes unit from database
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

    //deletes food form database
    public boolean deleteFood(int foodID){

        try{
            String deleteQuery = "DELETE FROM " + FOOD_NAME + " WHERE " +
                    FOOD_ID + " = " + "'" + foodID + "'";
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

    public boolean deleteRecipe(int recipeID){

        try{
            String deleteQuery = "DELETE FROM " + RECIPE_NAME + " WHERE " +
                    RECIPE_ID + " = " + "'" + recipeID + "'";
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

    public boolean deleteIngredient(int recipeingredientID){

        try{
            String deleteQuery = "DELETE FROM " + RECIPEINGREDIENTS_NAME + " WHERE " +
                    RECIPEINGREDIENTS_ID + " = " + "'" + recipeingredientID + "'";
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

    //Calculates KCAL for a Recipe
  /*  public void CalculateKcal (int recipeID, double addKcal, int oldKcal) {
        SQLiteDatabase db = this.getReadableDatabase();
        int newKcal = 0;

        newKcal = oldKcal + (int)addKcal;

        String updateSQL  = "UPDATE " + RECIPE_NAME +  " SET " +  RECIPE_COLUMN_KCAL + " = '" + newKcal
                + "' WHERE " + RECIPE_ID + "=" + recipeID;
        db.execSQL(updateSQL);
    }
*/
    //sets kcal in ingredientlist
    public void calculateRecipeKcal (int recipeID) {
        SQLiteDatabase db = this.getReadableDatabase();
        int newKcal = 0;

        String selectQuery = "SELECT SUM(" + RECIPEINGREDIENTS_KCAL + ") From " + RECIPEINGREDIENTS_NAME + " WHERE " + RECIPEINGREDIENTS_RECIPEID + " = " + recipeID;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do{
                newKcal = cursor.getInt(0);
            }while(cursor.moveToNext());
        }
        cursor.close();


        String updateSQL  = "UPDATE " + RECIPE_NAME +  " SET " +  RECIPE_COLUMN_KCAL + " = '" + newKcal
                + "' WHERE " + RECIPE_ID + "=" + recipeID;
        db.execSQL(updateSQL);
    }

    //updates a food object in database
    public void updateFood (int Id, String name, int kcal, double quantity, String unit) {
        SQLiteDatabase db = this.getReadableDatabase();
        String insertSQL  = "UPDATE " + FOOD_NAME +  " SET " +  FOOD_COLUMN_NAME + " = '" + name + "', " + FOOD_COLUMN_KCAL + " = " + kcal + ", "
                + FOOD_COLUMN_QUANTITY + " = " + quantity + ", "  + FOOD_COLUMN_UNIT + " = '" + unit
                + "' WHERE " + FOOD_ID + "=" + Id;
        db.execSQL(insertSQL);
    }

    //updates a portionsize in Recipe
    public void updatePortionInRecipe (int Id, double quantity) {
        SQLiteDatabase db = this.getReadableDatabase();
        String insertSQL  = "UPDATE " + RECIPEINGREDIENTS_NAME +  " SET " +  RECIPEINGREDIENTS_PORTION + " = '" + quantity
                + "' WHERE " + RECIPEINGREDIENTS_ID + " = " + Id;
        db.execSQL(insertSQL);
        DatabaseHandler db2 = this;
        //db2.calculateRecipeKcal();
    }

    //updates the settingsentry in database
    public void updateSettings (int maxkcal) {
        SQLiteDatabase db = this.getReadableDatabase();
        String insertSQL  = "UPDATE " + SETTINGS_NAME +  " SET " +  SETTINGS_MAXKCAL + " = " + maxkcal
                + " WHERE " + SETTINGS_ID + "=" + 1;
        db.execSQL(insertSQL);
    }

    //updates the unit
    public void updateUnit (int unitId, String unitName) {
        SQLiteDatabase db = this.getReadableDatabase();
        String insertSQL  = "UPDATE " + UNIT_NAME +  " SET " +  UNIT_COLUMN_NAME + " = '" + unitName
                + "' WHERE " + UNIT_COLUMN_ID + "=" + unitId;
        db.execSQL(insertSQL);
    }

}


