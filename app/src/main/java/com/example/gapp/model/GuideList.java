package com.example.gapp.model;

import android.util.Pair;

import java.util.List;

/**
 * Created by Jayne on 11/11/2015.
 * Contains a main heading and a description of that heading and a list of all inspirations under that heading. For example Entertainment
 * entertainment description, list of entertainment inspirations
 */

public class GuideList {

    private String  _id;
    private String _title;
    private String _description;
    private List<Pair<String,String>> _ideas;//a list of ideas under this heading

    //constructor, make the guide object from the database
    public GuideList()
    {


    }
    //make a guide object
    public void makeGuideList(String id,String title, String description,  List<Pair<String,String>> ideas ){
        _id=id;
        _title=title;
        _description=description;
        _ideas=ideas;
    }
    //gets
    public String getID() {
        return _id;
    }

    public String getTitle() {
        return _title;
    }
    public String getDescription() { return _description;}
    public List<Pair<String,String>> getIdeas()
    {
        return _ideas;
    }
}


