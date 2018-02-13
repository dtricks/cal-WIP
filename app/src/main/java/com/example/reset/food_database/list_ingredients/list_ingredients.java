package com.example.reset.food_database.list_ingredients;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.reset.food_database.BaseActivity;
import com.example.reset.food_database.DatabaseHandler;
import com.example.reset.food_database.R;
import com.example.reset.food_database.objects.Food;
import com.example.reset.food_database.objects.RecipeIngredient;

import java.util.ArrayList;
import java.util.List;

//import com.example.reset.food_database.util;

public class list_ingredients extends BaseActivity {

    Button addFood;
    ListView list;
    ListView ingredientList;
   // ListView listrecipetest;
    SearchView searchBar;

    List<Food> foodList = new ArrayList<Food>();
    List<RecipeIngredient> ingredientListtest = new ArrayList<RecipeIngredient>();

    DatabaseHandler database= new DatabaseHandler(list_ingredients.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_ingredients);

        addFood = (Button)findViewById(R.id.addingredient);
        list = (ListView)findViewById(R.id.foodlist);
        searchBar = (SearchView)findViewById(R.id.ingredientfilter);

        ingredientList = (ListView)findViewById(R.id.ingredientlist);

        addFood .setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), com.example.reset.food_database.add_food.init.class);
                startActivity(myIntent);
            }

        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String selectedItem = (String) parent.getItemAtPosition(position);

                final int selectedItemId = (int) parent.getItemIdAtPosition(position);


                AlertDialog.Builder builder = new AlertDialog.Builder(list_ingredients.this);
                builder.setTitle("Was möchten Sie mit tun?");
                //builder.setIcon(R.drawable.icon);

                builder.setPositiveButton("Hinzufügen",
                        new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int id)
                            {
                                //dialog.cancel();

                                int foodID = foodList.get(selectedItemId).getId();
                                //     Food food=database.getFood_new(foodID);

                                Food food = foodList.get(selectedItemId);

                                Toast.makeText(getApplicationContext(), food.toString(), Toast.LENGTH_LONG).show();


                                //TODO find out how to get Food from the database
                                //List<List<String>> allFood=database.getFood_PseudoObject();
                                // util.showMessage("database", allFood.toString(), list_food.this); //context is the problem here
                            }
                        });

                builder.setNeutralButton("edit",
                        new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int id)
                            {
                                Intent alertIntent = new Intent(getApplicationContext(), com.example.reset.food_database.edit_food.init.class);
                                alertIntent.putExtra("handoverId", ingredientListtest.get(selectedItemId).getId());
                                startActivity(alertIntent);
                                //Toast.makeText(getApplicationContext(), "TEST2", Toast.LENGTH_SHORT).show();
                                // context.startActivity(new Intent(context, Setup.class));
                                //dialog.cancel();
                            }
                        });

                builder.setNegativeButton("delete",
                        new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int id)
                            {
                                
                                int recipeID = ingredientListtest.get(selectedItemId).getId();


                                DatabaseHandler db = new DatabaseHandler(getApplicationContext());

                                if(db.deleteFood(recipeID)){
                                    Toast.makeText(getApplicationContext(), ingredientListtest.get(selectedItemId).getFoodname() + " wurde gelöscht!", Toast.LENGTH_SHORT).show();
                                    fillList(ingredientList);
                                }
                                else{
                                    Toast.makeText(getApplicationContext(), "Löschen nicht erfolgreich!", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
                builder.create().show();



            }
        });

        fillList(list);

        list.setTextFilterEnabled(true);
        setupSearchView();
    }

    private void setupSearchView() {
        searchBar.setIconifiedByDefault(false);
        searchBar.setSubmitButtonEnabled(true);
        searchBar.setQueryHint("Search Here");

        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)) {
                    list.clearTextFilter();
                } else {
                    list.setFilterText(newText.toString());
                }
                return true;
            }
        });
    }

    private void fillList(ListView list) {

        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
        foodList = db.getFoodList();
        List<String> foodAdapter = new ArrayList<String>();

        for (Food object: foodList) {
            double quantity = object.getQuantity();
            String quantityBearbeitet =  String.format(((quantity % 1.0D) == 0.0D) ? "%.0f" : "%.1f", quantity);
            String unitName = object.getUnit();
            String foodName = object.getName();
            String kcal = Integer.toString(object.getKcal());
            foodAdapter.add(quantityBearbeitet + " " + unitName + " " + foodName + " (" + kcal + " ktestcal)");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, foodAdapter);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        list.setAdapter(adapter);
    }
}
