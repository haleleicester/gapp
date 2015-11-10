package com.example.gapp;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gapp.model.Event;
import com.example.gapp.model.EventWriter;

public class CreateEvent1Activity extends AppCompatActivity {

    EditText t1;
    EditText t2;
    EditText t3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }
    public void onSaveEventClick(View view){
        // Toast.makeText(this, "Make new event", Toast.LENGTH_SHORT).show();
        EventWriter db = new EventWriter(this);
        t1 = (EditText) findViewById(R.id.eventname);
        t2 = (EditText) findViewById(R.id.eventlocation);
        t3 = (EditText) findViewById(R.id.eventdesc);
        String e_name = t1.getText().toString();
        String e_location = t2.getText().toString();
        String e_desc = t3.getText().toString();
       db.addEvent(e_name);
        // Toast.makeText(this, db.getSale(0).get_contents(), Toast.LENGTH_SHORT).show();
    }

}
