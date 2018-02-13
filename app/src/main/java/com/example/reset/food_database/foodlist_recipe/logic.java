package com.example.reset.food_database.foodlist_recipe;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.example.reset.food_database.DatabaseHandler;
import com.example.reset.food_database.objects.Food;
import com.example.reset.food_database.objects.RecipeIngredient;
import com.example.reset.food_database.objects.Recipes;

import android.app.AlertDialog;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oliver Gras
 */

//logic for adding foods from the list of foods to a recipe
public class logic {

    private Activity activity;
    private database data;
    private gui gui;
    private List<Food> foodList = new ArrayList<Food>();
    private List<RecipeIngredient> recipeIng = new ArrayList<RecipeIngredient>();
    List<String> stringList = new ArrayList<String>();
    private Recipes currentRecipe;


    public logic(Activity act, database data, gui gui) {
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

        fillList();

        gui.getFoodListRecipe().setTextFilterEnabled(true);
        setupSearchView();
    }

    //creates counter for to find the right id of the clicked value
    public void foodlistRecipeItemClicked(final String selectedItem) {


        int counter = 0;

        for (int i = 0; i < stringList.size(); i++) {
            if (stringList.get(i).equals(selectedItem)) {
                counter = i;
            }
        }

        final int selectedItemId = counter;

        AlertDialog.Builder alert = new AlertDialog.Builder(activity);

        final EditText edittext = new EditText(activity);
        edittext.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        edittext.setHint("Portionsize: like (1) or (0.5)");
        double alertInput = edittext.getInputType();
        String textfield =  String.format(((alertInput % 1.0D) == 0.0D) ? "%.0f" : "%.1f", alertInput);
        alert.setMessage("Please choose your portion size!");

        alert.setView(edittext);

        alert.setPositiveButton("Add to Recipe",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        int oldKcal = 0;
                        String textString = edittext.getText().toString();
                        if (textString.isEmpty() || Double.parseDouble(textString) == 0){
                            Toast.makeText(activity, "Please fill in your portionsize!", Toast.LENGTH_SHORT).show();
                        } else {
                            RecipeIngredient rec = new RecipeIngredient();
                            rec.setFoodname(foodList.get(selectedItemId).getName());
                            rec.setKcal(foodList.get(selectedItemId).getKcal());
                            rec.setQuantity(foodList.get(selectedItemId).getQuantity());
                            rec.setUnit(foodList.get(selectedItemId).getUnit());
                            rec.setPortion(Double.parseDouble(edittext.getText().toString()));
                            rec.setRecipeId(currentRecipe.getId());

                            int foodID = foodList.get(selectedItemId).getId();
                            Food food = foodList.get(selectedItemId);
                            oldKcal = currentRecipe.getKcal();
                            DatabaseHandler db = new DatabaseHandler(activity);

                            db.insertRecipeingredient(rec.getRecipeId(),rec.getFoodname(),rec.getKcal(),rec.getQuantity(),rec.getUnit(),rec.getPortion());
                            //db.setKcalForIngredient(rec.getId(),rec.getKcal());
                            db.calculateRecipeKcal(currentRecipe.getId());

                           Toast.makeText(activity, rec.toString(), Toast.LENGTH_LONG).show();
                            // Toast.makeText(activity, recipeIng.toString(), Toast.LENGTH_LONG).show();
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

    //sets up the search
    private void setupSearchView() {
        gui.getFoodSearch().setIconifiedByDefault(false);
        gui.getFoodSearch().setSubmitButtonEnabled(true);
        gui.getFoodSearch().setQueryHint("Search Here");

    }

    //fills list with foods from the database
    private void fillList() {

        DatabaseHandler db = new DatabaseHandler(activity);
        foodList = db.getFoodList();
        List<String> foodAdapter = new ArrayList<String>();

        for (Food object: foodList) {
            double quantity = object.getQuantity();
            String quantityBearbeitet =  String.format(((quantity % 1.0D) == 0.0D) ? "%.0f" : "%.1f", quantity);
            String unitName = object.getUnit();
            String foodName = object.getName();
            String kcal = Integer.toString(object.getKcal());
            foodAdapter.add(quantityBearbeitet + " " + unitName + " " + foodName + " (" + kcal + " kcal)");
            stringList.add(quantityBearbeitet + " " + unitName + " " + foodName + " (" + kcal + " kcal)");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity, android.R.layout.simple_list_item_1, foodAdapter);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        gui.getFoodListRecipe().setAdapter(adapter);
    }
}


