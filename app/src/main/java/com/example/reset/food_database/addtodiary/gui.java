package com.example.reset.food_database.addtodiary;

import android.app.DatePickerDialog;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.reset.food_database.R;
import com.example.reset.food_database.addtodiary.init;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Denis Kerner
 */

public class gui {

    //initalizing the gui objects
    private EditText date_textfield;
    private EditText portion_textfield;
    private TextView name_content_textview;
    private TextView kcal_content_textview;
    private TextView quantity_content_textview;
    private TextView unit_content_textview;
    private Button submitfoodbutton;


    Calendar myCalendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener date= new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, day);
            updateLabel();
        }
    };
    //finding gui objects in layout
    public gui(final init activity) {
        activity.setContentView(R.layout.addtodiary_food);
        date_textfield = (EditText) activity.findViewById(R.id.date_textfield);
        portion_textfield = (EditText) activity.findViewById(R.id.portion_textfield);
        name_content_textview = (TextView) activity.findViewById(R.id.name_content_textview);
        kcal_content_textview = (TextView) activity.findViewById(R.id.kcal_content_textview);
        quantity_content_textview = (TextView) activity.findViewById(R.id.quantity_content_textview);
        unit_content_textview = (TextView) activity.findViewById(R.id.unit_content_textview);
        submitfoodbutton = (Button) activity.findViewById(R.id.submitfoodbutton);

        date_textfield.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new DatePickerDialog(activity , date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH) ).show();

            }
        });
    }


    private void updateLabel(){
        String format="dd.MM.yyyy";
        SimpleDateFormat sdf= new SimpleDateFormat(format);

        date_textfield.setText(sdf.format(myCalendar.getTime()));
    }
    //getter & setter


    public EditText getDate_textfield() {
        return date_textfield;
    }

    public void setDate_textfield(EditText date_textfield) {
        this.date_textfield = date_textfield;
    }

    public EditText getPortion_textfield() {
        return portion_textfield;
    }

    public void setPortion_textfield(EditText portion_textfield) {
        this.portion_textfield = portion_textfield;
    }

    public TextView getName_content_textview() {
        return name_content_textview;
    }

    public void setName_content_textview(TextView name_content_textview) {
        this.name_content_textview = name_content_textview;
    }

    public TextView getKcal_content_textview() {
        return kcal_content_textview;
    }

    public void setKcal_content_textview(TextView kcal_content_textview) {
        this.kcal_content_textview = kcal_content_textview;
    }

    public TextView getQuantity_content_textview() {
        return quantity_content_textview;
    }

    public void setQuantity_content_textview(TextView quantity_content_textview) {
        this.quantity_content_textview = quantity_content_textview;
    }

    public TextView getUnit_content_textview() {
        return unit_content_textview;
    }

    public void setUnit_content_textview(TextView unit_content_textview) {
        this.unit_content_textview = unit_content_textview;
    }

    public Button getSubmitfoodbutton() {
        return submitfoodbutton;
    }

    public void setSubmitfoodbutton(Button submitfoodbutton) {
        this.submitfoodbutton = submitfoodbutton;
    }
}
