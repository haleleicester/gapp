package com.example.gapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gapp.model.Event;
import com.example.gapp.model.EventWriter;

import java.util.ArrayList;

public class CreateEvent1Activity extends AppCompatActivity {

    EditText e_name;
    EditText e_loc;
    EditText e_date;
    EditText e_time;
    EditText e_cat;
    EditText e_desc;
    EditText e_tgtamt;
    EditText e_amtrsd;
    EditText e_ptrc;
    public ArrayList<Boolean> checked;
    public int result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        result = 0;
        checked = new ArrayList<>();
        checked.add(false);
        checked.add(false);
        checked.add(false);




    }
    public void onSaveEventClick(View view){
        EventWriter db = new EventWriter(this);


        e_name = (EditText) findViewById(R.id.eventname);
        e_loc = (EditText) findViewById(R.id.eventlocation);
        e_date = (EditText) findViewById(R.id.eventdate);
        e_time = (EditText) findViewById(R.id.eventtime);
        e_cat= (EditText) findViewById(R.id.category);
        e_desc= (EditText) findViewById(R.id.eventdesc);;
        e_tgtamt= (EditText) findViewById(R.id.eventtarget);;
        e_amtrsd = (EditText) findViewById(R.id.eventamtraised);
        //e_ptrc = (EditText) findViewById(R.id.PTcheckBox);;



        String e_names = e_name.getText().toString();
        String e_locs = e_loc.getText().toString();
        String e_dates = e_date.getText().toString();
        String e_times = e_time.getText().toString();
        String e_cats = e_cat.getText().toString();
        String e_descs = e_desc.getText().toString();
        String e_tgtamts = e_tgtamt.getText().toString();
        String e_amtrsds = e_amtrsd.getText().toString();
        //String e_ptrcs = e_ptrc.getText().toString();
       db.addEvent(e_names, e_locs, e_dates,e_times,e_cats,e_descs,e_tgtamts,e_amtrsds, result);
        // Toast.makeText(this, db.getSale(0).get_name(), Toast.LENGTH_SHORT).show();
    }

    // When pulling info from database this is where the checklist will be calculated
    public void setToDo(){



    }


    public void onToDoClick(View view){

        String[] topics = {"Advertise", "Book Venue", "Sell tickets", };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        boolean[] mChecked = {checked.get(0),checked.get(1), checked.get(2)};

        // Set the dialog title
        builder.setTitle("To do list")
                // Specify the list array, the items to be selected by default (null for none),
                // and the listener through which to receive callbacks when items are selected
                .setMultiChoiceItems(topics, mChecked, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                if (isChecked) {
                                    // If the user checked the item, add it to the selected items
                                    checked.set(which, true);
                                    Log.d("gapp_debug", checked.toString());
                                } else {// if (mSelectedItems.contains(which)) {
                                    // Else, if the item is already in the array, remove it
                                    checked.set(which, false);
                                    Log.d("gapp_debug", checked.toString());
                                }
                            }
                        })
                        // Set the action buttons
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK, so save the mSelectedItems results somewhere
                        // or return them to the component that opened the dialog
                        Log.d("Going to Database", "Now");
                        toDoDatabase();


                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        builder.create().show();
    }


    public void toDoDatabase(){

        /**
         * Todo list is saved via a calculating how many checkboxes have been pressed.
         * This is calculated by giving the boolean a int value. E.G. Advertise = 2 Book Venue = 4
         * and so on.
         *
         * When pulling from the database a calculation should work out what has been posted. E.G. 6 = Advertise and Book Venue.
         */

        int power = 2;

        for(int i = 0; i < checked.size(); i++){
            if(checked.get(i) == true) {
                result = result + power;
                power = power * 2;
            }
        }

        Log.d("Going to Database", "Success");



    }

}
