package com.example.gapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.gapp.model.Event;
import com.example.gapp.model.EventWriter;

import java.util.ArrayList;
import java.util.List;

public class ViewActivity extends AppCompatActivity {

    List<Event> events;
    TextView database;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        EventWriter db = new EventWriter(this);
        events = db.getEvents();


        database = (TextView) findViewById(R.id.viewText);
        i = 0;

        if(events.size() != 0 ) {
            Event e = events.get(i);
            database.setText(e.get_name() + "\n" + e.getLocation() + "\n" + e.getDate() + "\n" + e.getTime() + "\n" + e.getCategory() + "\n" + e.getDescription() + "\n" + e.getTarget() + "\n" + e.getAmountR() + "\n" + e.getTodo());
        }
//        for (Event e : events) {
//            Log.d("gapp_debug", e.get_id() + ": " + e.get_name() + " " + e.getLocation() + " " + e.getDate() + " " + e.getTime() + " " + e.getCategory() + " " +  e.getDescription() + " " + e.getTarget() + " " + e.getAmountR() + e.getTodo());
//        }

    }
    public void onViewEventsPress(View view){
        Intent myIntent = new Intent(view.getContext(), ViewActivity.class);
        startActivityForResult(myIntent, 0);
    }
    public void onNextClick(View view){


        if(events.size() != 0 && i + 1 != events.size()) {
            i++;
            Event e = events.get(i);
            database.setText(e.get_name() + "\n" + e.getLocation() + "\n" + e.getDate() + "\n" + e.getTime() + "\n" + e.getCategory() + "\n" + e.getDescription() + "\n" + e.getTarget() + "\n" + e.getAmountR() + "\n" + e.getTodo());
        }

    }

    public void onPreviousClick(View view){

        if(events.size() != 0 && i - 1 >= 0) {
            i--;
            Event e = events.get(i);
            database.setText(e.get_name() + "\n" + e.getLocation() + "\n" + e.getDate() + "\n" + e.getTime() + "\n" + e.getCategory() + "\n" + e.getDescription() + "\n" + e.getTarget() + "\n" + e.getAmountR() + "\n" + e.getTodo());
        }

    }

}
