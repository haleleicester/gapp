package com.example.gapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class InspireActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspire);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void onSalesClick(View view){
        Intent myIntent = new Intent(view.getContext(), SalesActivity.class);
        startActivityForResult(myIntent, 0);
    }

    public void onServicesClick(View view){
        Toast.makeText(this,"Services has been clicked", Toast.LENGTH_SHORT).show();

    }

    public void onTGClick(View view){
        Toast.makeText(this,"Trust/Grant has been clicked", Toast.LENGTH_SHORT).show();
    }

    public void onEntertainmentClick(View view){
        Toast.makeText(this,"Entertainment has been clicked", Toast.LENGTH_SHORT).show();
    }

}
