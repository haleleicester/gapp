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

    EditText t1;
    EditText t2;
    EditText t3;
    EditText t4;
    public ArrayList<Boolean> checked;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        checked = new ArrayList<>();
        checked.add(false);
        checked.add(false);
        checked.add(false);



    }
    public void onSaveEventClick(View view){
        // Toast.makeText(this, "Make new event", Toast.LENGTH_SHORT).show();
        EventWriter db = new EventWriter(this);
        t1 = (EditText) findViewById(R.id.eventname);
        t2 = (EditText) findViewById(R.id.eventlocation);
        t3 = (EditText) findViewById(R.id.eventdate);
        String e_name = t1.getText().toString();
        String e_location = t2.getText().toString();
        String e_date = t3.getText().toString();
       // db.addEvent(e_name, e_location, e_date);
        // Toast.makeText(this, db.getSale(0).get_contents(), Toast.LENGTH_SHORT).show();
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

        for(int i = 0; i < checked.size(); i++){

        }


    }

}
