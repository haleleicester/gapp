package com.example.gapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.gapp.model.Sale;
import com.example.gapp.model.SalesGrabber;

public class SalesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void onNewEventClick(View view){
       // Toast.makeText(this, "Make new event", Toast.LENGTH_SHORT).show();
        SalesGrabber db = new SalesGrabber(this);

        db.addSale(new Sale("Test Hello World"));
        Toast.makeText(this, db.getSale(0).get_contents(), Toast.LENGTH_SHORT).show();
    }

}
