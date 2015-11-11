package com.example.gapp;
//lists the guides under a particular category, for example entertainment.

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gapp.model.GuideList;

import java.util.ArrayList;
import java.util.List;

public class GuideListActivity extends AppCompatActivity {

    private String catTitle;
    private String catDesc;
    private List<Pair<String, String>> ideas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        if (null != intent) {
            String categoryID = intent.getStringExtra("categoryId");

            //extract the data from the database to display the entertainment activities
            GuideDataBaseHelper dbHelper = new GuideDataBaseHelper(this);
            GuideList newGuideList = dbHelper.getGuideList(categoryID);

            catTitle = newGuideList.getTitle();
            catDesc = newGuideList.getDescription();
            ideas = newGuideList.getIdeas();

            TextView catTitleView = (TextView) findViewById(R.id.cat_title);
            catTitleView.setText(catTitle);
            TextView catDescView = (TextView) findViewById(R.id.cat_description);
            catDescView.setText(catDesc);

            ListView ideaList = (ListView) findViewById(R.id.idea_list);
            final GuideListAdapter adapter = new GuideListAdapter(this);
            adapter.setData(newGuideList.getIdeas());
            ideaList.setAdapter(adapter);

            ideaList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String itemID = ((Pair<String, String>) adapter.getItem(position)).first;
                    Intent go = new Intent(GuideListActivity.this, GuideItemActivity.class);
                    go.putExtra("itemId", itemID);
                    startActivity(go);
                }
            });
        }

    }

    public class GuideListAdapter extends BaseAdapter {

        private List<Pair<String, String>> data = new ArrayList<>();
        private Context context;

        public GuideListAdapter(Context context) {
            this.context = context;
        }

        public void setData(List<Pair<String, String>> data) {
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
            if (view == null) view = new TextView(context);

            String idea = ((Pair<String, String>) getItem(position)).second;

            view.setText(idea);

            return view;
        }
    }


}
