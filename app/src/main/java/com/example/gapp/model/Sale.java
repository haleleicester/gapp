package com.example.gapp.model;

/**
 * Created by liam on 10/11/15.
 */
public class Sale {

    private int  id;
    private String contents;

    public Sale(String contents){

        this.contents = contents;

    }

    public void set_id(int id){
        this.id =id;
    }

    public int get_id() {
        return id;
    }

    public String get_contents() {
        return contents;
    }

    public void set_contents(String _contents) {
        this.contents = _contents;
    }
}
