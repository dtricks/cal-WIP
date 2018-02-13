package com.example.reset.food_database.list_ingredients;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.InputType;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.example.reset.food_database.DatabaseHandler;
import com.example.reset.food_database.list_ingredients.gui;
import com.example.reset.food_database.objects.Food;
import com.example.reset.food_database.objects.RecipeIngredient;
import com.example.reset.food_database.objects.Recipes;

import java.util.ArrayList;
import java.util.List;

//import com.example.reset.food_database.objects.Recipes;

//import com.example.reset.food_database.objects.Food;


/**
 * Created by Oliver Gras
 */

//logic for the list of recipes
public class logic {

    private Activity activity;
    private database data;
    private com.example.reset.food_database.list_ingredients.gui gui;
    private List<RecipeIngredient> ingredientList = new ArrayList<RecipeIngredient>();
    List<String> stringList = new ArrayList<String>();
    private Recipes currentRecipe;

    public logic(Activity act, database data, com.example.reset.food_database.list_ingredients.gui gui) {
        super();
        this.data = data;
        this.gui = gui;
        activity = act;

        //getting the Id of the Recipe
        Intent intent = activity.getIntent();
        if (intent != null) {
            DatabaseHandler db = new DatabaseHandler(activity);
            db.getReadableDatabase();
            currentRecipe = db.getRecipe_new(intent.getIntExtra("handoverId", 0));
        }

        gui.setRecipeNameText("Ingredients of: " + currentRecipe.getName());

        fillList();

        gui.getIngredientList().setTextFilterEnabled(true);
        setupSearchView();
    }

    //leads user to add_Recipe
    public void addRecipeButtonClicked(){
        Intent alertIntent = new Intent(activity, com.example.reset.food_database.foodlist_recipe.init.class);
        alertIntent.putExtra("handoverId", currentRecipe.getId());
        activity.startActivity(alertIntent);
        //Intent myIntent = new Intent(activity, com.example.reset.food_database.recipe_addtolist.init.class);
       // activity.startActivity(myIntent);
    }

    //creates counter for Searchview to get the right id while using the filter function
    public void itemClicked(String selectedItem){

        int counter = 0;

        for(int i=0; i < stringList.size(); i++)
        {
            if(stringList.get(i).equals(selectedItem))
            {
                counter = i;
            }
        }

        final int selectedItemId = counter;

        //opens Dialog with the 3 choices edit food, delete food, add food to diary
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("What would You like to do with your choosen food?");

        //leads user to addtodiary
        builder.setPositiveButton("Set Portion size",
                new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id) {

                        int recipeID = ingredientList.get(selectedItemId).getId();

                        RecipeIngredient recipesingredient = ingredientList.get(selectedItemId);

                        Toast.makeText(activity, recipesingredient.toString(), Toast.LENGTH_LONG).show();
                        updatePortion(recipeID);
                    }
                });

        //leads user to editRecipe + handover the chosen recipes id
        builder.setNeutralButton("OK",
                new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                       // Intent alertIntent = new Intent(activity, com.example.reset.food_database.edit_food.init.class);
                        //alertIntent.putExtra("handoverId", ingredientList.get(selectedItemId).getId());
                       // activity.startActivity(alertIntent);
                        //DatabaseHandler db = new DatabaseHandler(activity);
                       // db.CalculateKcal(currentRecipe.getId());
                    }
                });

        //deletes the clicked Recipe
        builder.setNegativeButton("Delete",
                new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        int recipeingredientID = ingredientList.get(selectedItemId).getId();


                        DatabaseHandler db = new DatabaseHandler(activity);

                        if(db.deleteIngredient(recipeingredientID)){
                            Toast.makeText(activity, ingredientList.get(selectedItemId).getFoodname() + " has been successfully deleted!", Toast.LENGTH_SHORT).show();
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
        gui.getIngredientfilter().setIconifiedByDefault(false);
        gui.getIngredientfilter().setSubmitButtonEnabled(true);
        gui.getIngredientfilter().setQueryHint("Search Here");

    }

    private void updatePortion(final int ingredientid){

        AlertDialog.Builder alert = new AlertDialog.Builder(activity);

        final EditText edittext = new EditText(activity);
        edittext.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        edittext.setHint("Portionsize: like (1) or (0.5)");
        double alertInput = edittext.getInputType();
        String textfield =  String.format(((alertInput % 1.0D) == 0.0D) ? "%.0f" : "%.1f", alertInput);
        alert.setMessage("Please choose your portion size!");

        alert.setView(edittext);

        alert.setPositiveButton("Update Portion",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                int oldKcal = 0;
                String textString = edittext.getText().toString();
                if (textString.isEmpty() || Double.parseDouble(textString) == 0){
                    Toast.makeText(activity, "Please fill in your new portionsize!", Toast.LENGTH_SHORT).show();
                } else {

                    DatabaseHandler db = new DatabaseHandler(activity);
                    db.updatePortionInRecipe(ingredientid,Double.parseDouble(edittext.getText().toString()));
                    db.calculateRecipeKcal(currentRecipe.getId());
                    fillList();
                }
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // cancel
            }
        });
        alert.show();
    }


    //filling the list with Ingredients from database
    private void fillList() {

        DatabaseHandler db = new DatabaseHandler(activity);
        ingredientList = db.getIngredientList(currentRecipe.getId());
        List<String> foodAdapter = new ArrayList<String>();

        for (RecipeIngredient object: ingredientList) {
            double quantity = object.getKcal();
            //String quantityBearbeitet =  String.format(((quantity % 1.0D) == 0.0D) ? "%.0f" : "%.1f", quantity);
            String unitName = object.getUnit();
            String recipeName = object.getFoodname();
            String kcal = Integer.toString(object.getKcal());
            int kcaltmp = object.getKcal();

           foodAdapter.add(recipeName + " (" + kcaltmp + " kcal)");
            stringList.add(recipeName +  " (" + kcaltmp + " kcal)");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity, android.R.layout.simple_list_item_1, foodAdapter);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        gui.getIngredientList().setAdapter(adapter);
    }

}
