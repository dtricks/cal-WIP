package com.example.reset.food_database.foodlist_recipe;

import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.reset.food_database.R;

/**
 * Created by Oliver Gras
 */

public class gui {

    //initalizing the gui objects
    private SearchView foodlistSearchRecipe;
    private ListView foodListRecipe;

    //finding gui objects in layout
    public gui(init activity) {
        activity.setContentView(R.layout.foodlist_recipe);
        foodlistSearchRecipe = (SearchView) activity.findViewById(R.id.foodfilter_recipe);
        foodListRecipe = (ListView) activity.findViewById(R.id.foodlist_recipe);

    }

    //getter & setter
    public SearchView getFoodSearch() {
        return foodlistSearchRecipe;
    }

    public SearchView getFoodlistSearchRecipe() {
        return foodlistSearchRecipe;
    }

    public void setFoodlistSearchRecipe(SearchView foodlistSearchRecipe) {
        this.foodlistSearchRecipe = foodlistSearchRecipe;
    }

    public ListView getFoodListRecipe() {
        return foodListRecipe;
    }

    public void setFoodListRecipe(ListView foodListRecipe) {
        this.foodListRecipe = foodListRecipe;
    }
}
