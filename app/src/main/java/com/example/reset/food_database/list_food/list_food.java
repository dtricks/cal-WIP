package com.example.reset.food_database.list_food;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
import android.app.AlertDialog;


import com.example.reset.food_database.DatabaseHandler;
import com.example.reset.food_database.R;
import com.example.reset.food_database.add_food.activity_add_food;
import com.example.reset.food_database.objects.Food;
//import com.example.reset.food_database.util;

import java.util.ArrayList;
import java.util.List;

public class list_food extends AppCompatActivity {

    Button addFood;
    ListView list;
    SearchView searchBar;


    DatabaseHandler database= new DatabaseHandler(list_food.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_food);

    addFood = (Button)findViewById(R.id.addfood);
        list = (ListView)findViewById(R.id.foodlist);
        searchBar = (SearchView)findViewById(R.id.foodfilter);

        addFood .setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), activity_add_food.class);
                startActivity(myIntent);
            }

        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String selectedItem = (String) parent.getItemAtPosition(position);

                AlertDialog.Builder builder = new AlertDialog.Builder(list_food.this);
                builder.setTitle("Was möchten Sie mit tun?");
                //builder.setIcon(R.drawable.icon);

                builder.setPositiveButton("Hinzufügen",
                        new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int id)
                            {
                                //Toast.makeText(getApplicationContext(), "TEST1", Toast.LENGTH_SHORT).show();
                                //dialog.cancel();

                                Food food=database.getFood_new(0);

                                Toast.makeText(getApplicationContext(), food.toString(), Toast.LENGTH_LONG).show();


                                //TODO find out how to get Food from the database
                                //List<List<String>> allFood=database.getFood_PseudoObject();
                               // util.showMessage("database", allFood.toString(), list_food.this); //context is the problem here
                            }
                        });

                builder.setNeutralButton("Bearbeiten",
                        new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int id)
                            {
                                Toast.makeText(getApplicationContext(), "TEST2", Toast.LENGTH_SHORT).show();
                               // context.startActivity(new Intent(context, Setup.class));
                                //dialog.cancel();
                            }
                        });

                builder.setNegativeButton("Löschen",
                        new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int id)
                            {
                                String str = selectedItem;
                                String[] splited = str.split("\\s+");
                                String quantity = splited[0];
                                String unit = splited[1];
                                String name = splited[2];
                                String kcal = splited[3].substring(1);
                                Toast.makeText(getApplicationContext(), quantity + unit + name + kcal, Toast.LENGTH_SHORT).show();

                                DatabaseHandler db = new DatabaseHandler(getApplicationContext());

                                if(db.deleteFood(name, kcal, quantity, unit)){
                                    Toast.makeText(getApplicationContext(), name + " wurde gelöscht!", Toast.LENGTH_SHORT).show();
                                    fillList(list);
                                }
                                else{
                                    Toast.makeText(getApplicationContext(), "Löschen nicht erfolgreich!", Toast.LENGTH_SHORT).show();
                                }


                                // dialog.cancel();
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
        List<String> foodList = new ArrayList<String>();
        foodList = db.getFood();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, foodList);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        list.setAdapter(adapter);
    }
}
