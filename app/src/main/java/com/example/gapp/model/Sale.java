package com.example.gapp.model;

/**
 * Created by liam on 10/11/15.
 */
public class Sale {

    private int  _id;
    private String _contents;

    public Sale(String _contents){

        this._contents = _contents;

    }

    public int get_id() {
        return _id;
    }

    public String get_contents() {
        return _contents;
    }

    public void set_contents(String _contents) {
        this._contents = _contents;
    }
}
