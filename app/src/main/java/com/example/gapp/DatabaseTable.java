package com.example.gapp;

/**
 * Created by liam on 10/11/15.
 */
public class DatabaseTable
{

    private int id;
    private String name;
    private String content;



    public DatabaseTable(int id, String name, String content){

        this.id = id;
        this.name = name;
        this.content = content;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content = content;
    }


}
