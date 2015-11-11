package com.example.gapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.gapp.model.Event;
import com.example.gapp.model.EventWriter;

import java.util.List;

public class ViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        EventWriter db = new EventWriter(this);
        List<Event> events = db.getEvents();

        for (Event e : events) {
            Log.d("gapp_debug", e.get_id() + ": " + e.get_name() + " " + e.getLocation() + " " + e.getDate() + " " + e.getTime() + " " + e.getCategory() + " " +  e.getDescription() + " " + e.getTarget() + " " + e.getAmountR() + e.getTodo());
        }


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
