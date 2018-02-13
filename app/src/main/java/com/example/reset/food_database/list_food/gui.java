
package com.example.reset.food_database.list_food;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;

import com.example.reset.food_database.R;


/**
 * Created by Oliver Gras
 */


public class gui {

    //initalizing the gui objects
    private SearchView foodSearch;
    private ListView foodList;
    private Button addFood;

    //finding gui objects in layout
    public gui(init activity) {
        activity.setContentView(R.layout.list_food);
        foodSearch = (SearchView) activity.findViewById(R.id.foodfilter);
        foodList = (ListView) activity.findViewById(R.id.foodlist);
        addFood = (Button) activity.findViewById(R.id.addfood);

    }

    //getter & setter
    public SearchView getFoodSearch() {
        return foodSearch;
    }

    public void setFoodSearch(SearchView foodSearch) {
        this.foodSearch = foodSearch;
    }

    public ListView getFoodList() {
        return foodList;
    }

    public void setFoodList(ListView foodList) {
        this.foodList = foodList;
    }

    public Button getAddFood() {
        return addFood;
    }

    public void setAddFood(Button addFood) {
        this.addFood = addFood;
    }
}

