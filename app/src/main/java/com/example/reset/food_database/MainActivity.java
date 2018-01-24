package com.example.reset.food_database;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;
import android.app.AlertDialog;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button addFood;
    ListView list;
    SearchView searchBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Was möchten Sie mit tun?");
                //builder.setIcon(R.drawable.icon);

                builder.setPositiveButton("Hinzufügen",
                        new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int id)
                            {
                                Toast.makeText(getApplicationContext(), "TEST1", Toast.LENGTH_SHORT).show();
                                //dialog.cancel();
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
