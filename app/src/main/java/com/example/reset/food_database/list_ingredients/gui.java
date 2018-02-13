
package com.example.reset.food_database.list_ingredients;

import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.reset.food_database.R;


/**
 * Created by Oliver Gras
 */


public class gui {

    //initalizing the gui objects
    private SearchView ingredientfilter;
    private ListView ingredientList;
    private Button addIngredient;
    private TextView recipeName;

    //finding gui objects in layout
    public gui(init activity) {
        activity.setContentView(R.layout.recipe_ingredients);
        recipeName = (TextView) activity.findViewById(R.id.recipeNameHeader);
        ingredientfilter = (SearchView) activity.findViewById(R.id.ingredientfilter);
        ingredientList = (ListView) activity.findViewById(R.id.ingredientlist);
        addIngredient = (Button) activity.findViewById(R.id.addingredient);



    }

    //getter & setter
    public SearchView getIngredientfilter() {
            return ingredientfilter;
    }

    public void setIngredientfilter(SearchView ingredientfilter) {
        this.ingredientfilter = ingredientfilter;
    }

    public ListView getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(ListView ingredientList) {
        this.ingredientList = ingredientList;
    }

    public Button getAddIngredient() {
        return addIngredient;
    }

    public void setAddIngredient(Button addIngredient) {
        this.addIngredient = addIngredient;
    }

    public TextView getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(TextView recipeName) {
        this.recipeName = recipeName;
    }

    public void setRecipeNameText(String text){
        recipeName.setText(text);

    }


}

