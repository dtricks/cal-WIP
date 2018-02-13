
package com.example.reset.food_database.list_recipes;

import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.reset.food_database.R;


/**
 * Created by Oliver Gras
 */


public class gui {

    //initalizing the gui objects
    private SearchView recipeSearch;
    private ListView recipeList;
    private Button addRecipe;

    //finding gui objects in layout
    public gui(init activity) {
        activity.setContentView(R.layout.list_recipes);
        recipeSearch = (SearchView) activity.findViewById(R.id.ingredientfilter);
        recipeList = (ListView) activity.findViewById(R.id.recipelist);
        addRecipe = (Button) activity.findViewById(R.id.addingredient);

    }

    //getter & setter
    public SearchView getRecipeSearch() {
        return recipeSearch;
    }

    public void setRecipeSearch(SearchView recipeSearch) {
        this.recipeSearch = recipeSearch;
    }

    public ListView getRecipeList() {
        return recipeList;
    }

    public void setRecipeList(ListView recipeList) {
        this.recipeList = recipeList;
    }

    public Button getAddRecipe() {
        return addRecipe;
    }

    public void setAddRecipe(Button addRecipe) {
        this.addRecipe = addRecipe;
    }
}

