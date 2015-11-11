package com.example.gapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gapp.model.Guide;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class GuideItemActivity extends AppCompatActivity {
    private String guideID;
    private String guideTitle;
    private String guideHowItWorks;
    private String guideExpectedAmount;
    private List<String> tips = new ArrayList<>();//vector of tips for this guide

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entertainment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //extract the data from the database to display the entertainment activities
        GuideDataBaseHelper dbHelper = new GuideDataBaseHelper(this);
        Guide newGuide=dbHelper.getGuide("G1");
        guideID=newGuide.getID();
        guideTitle=newGuide.getTitle();
        guideHowItWorks=newGuide.getHowItWorks();
        guideExpectedAmount=newGuide.getExpectedAmount();
        tips=newGuide.getTips();
        TextView guideTitleView = (TextView) findViewById(R.id.guide_title);
        guideTitleView.setText(guideTitle);
        TextView guideExpectedAmountView = (TextView) findViewById(R.id.guide_expected_amount);
        guideExpectedAmountView.setText(guideExpectedAmount);
        TextView guideHowItWorksView= (TextView) findViewById(R.id.guide_how_it_works);
        guideHowItWorksView.setText(Html.fromHtml(guideHowItWorks));

        ListView guideTipsListView = (ListView) findViewById(R.id.tips_list);
        TipsListAdapter tipsListAdapter = new TipsListAdapter(this);
        tipsListAdapter.setData(tips);
        guideTipsListView.setAdapter(tipsListAdapter);

    }

    public void onNewEventClick(View view){
        // Toast.makeText(this, "Make new event", Toast.LENGTH_SHORT).show();
        //  SalesGrabber db = new SalesGrabber(this);

        //  db.addSale(new Sale("Test Hello World"));
        //  Toast.makeText(this, db.getSale(0).get_contents(), Toast.LENGTH_SHORT).show();
    }

    public class TipsListAdapter extends BaseAdapter {

        private List<String> data = new ArrayList<>();
        private Context context;

        public TipsListAdapter(Context context) {
            this.context = context;
        }

        public void setData(List<String> data) {
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView view = convertView instanceof TextView ? (TextView) convertView : null;
            if (convertView == null) view = new TextView(context);

            String tip = (String) getItem(position);

            view.setText(tip);

            return view;
        }
    }

}
