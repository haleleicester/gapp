package com.example.gapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.IOException;
import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((Toolbar) findViewById(R.id.toolbar)).setTitle("Project Trust :: Gapp");
    }

    public void onInspirePress(View view){
        Intent myIntent = new Intent(view.getContext(), InspireActivity.class);
        startActivityForResult(myIntent, 0);
    }

    public void onNewEventPress(View view){
        Intent myIntent = new Intent(view.getContext(), CreateEvent1Activity.class);
        startActivityForResult(myIntent, 0);
    }

}
