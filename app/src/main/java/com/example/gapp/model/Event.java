package com.example.gapp.model;

/**
 * Created by liam on 10/11/15.
 */
public class Event {

    private int  _id;
    private String _name;

    public Event(String _name){

        this._name = _name;

    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }
}
