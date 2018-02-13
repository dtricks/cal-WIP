
package com.example.reset.food_database.statistics;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.reset.food_database.R;


/*
 * Created by Denis Kerner
 */


public class gui {

    //initalizing the gui objects
    private TextView last7days;
    private TextView last14days;
    private TextView last30days;

    //finding gui objects in layout
    public gui(init activity) {
        activity.setContentView(R.layout.statistics);
        last7days = (TextView) activity.findViewById(R.id.last7days_content);
        last14days = (TextView) activity.findViewById(R.id.last14days_content);
        last30days = (TextView) activity.findViewById(R.id.last30days_content);

    }

    //getter & setter
    public TextView getLast7days() {
        return last7days;
    }

    public void setLast7days(TextView last7days) {
        this.last7days = last7days;
    }

    public TextView getLast14days() {
        return last14days;
    }

    public void setLast14days(TextView last14days) {
        this.last14days = last14days;
    }

    public TextView getLast30days() {
        return last30days;
    }

    public void setLast30days(TextView last30days) {
        this.last30days = last30days;
    }
}

