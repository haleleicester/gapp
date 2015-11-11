package com.example.gapp;
//lists the guides under a particular category, for example entertainment.
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class GuideListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //display the list of items for this event
       //first get the event id from the bundle
        Intent intent = getIntent();
        if (null != intent) {
            String inspirationID= intent.getStringExtra("inspireID");
            //display on the page for now

            TextView guideTitleView = (TextView) findViewById(R.id.debug_inspiration_id);
            guideTitleView.setText(inspirationID);

        }

    }

}
