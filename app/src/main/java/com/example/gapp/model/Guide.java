package com.example.gapp.model;

import java.util.List;

/**
 * Created by Jayne on 11/11/2015.
 * This class creates a guide object which is displayed on the individual guide page, for example the quiz night page
 */
public class Guide {

    private String  _id;
    private String _title;
    private String _expectedAmount;//an array of tips
    private String _howItWorks;//an array of tips
    private List<String> _tips;//an array of tips

    //constructor, make the guide object from the database
    public Guide()
    {


    }
    //make a guide object
    public void makeGuide(String id,String title, String expectedAmount, String howItWorks, List<String> tips ){
        _id=id;
        _title=title;
        _expectedAmount=expectedAmount;
        _howItWorks=howItWorks;
        _tips=tips;
    }
    //gets
    public String getID() {
        return _id;
    }

    public String getTitle() {
        return _title;
    }
    public String getExpectedAmount() {
        return _expectedAmount;
    }
    public String getHowItWorks() {
        return _howItWorks;
    }
    public List<String> getTips()
    {
        return _tips;
    }

}
