package com.example.gapp.model;

/**
 * Created by liam on 10/11/15.
 */
public class Event {

    private int  _id;
    private String _name;
    private String location;
    private String date;
    private String time;
    private String category;
    private String description;
    private String target;
    private String amountR;
    private int todo;

    public Event(String _name, String location, String date, String time, String category, String description, String target, String amountR, int todo){

        this._name = _name;
        this.location = location;
        this.date = date;
        this.time = time;
        this.category = category;
        this.description = description;
        this.target = target;
        this.amountR = amountR;
        this.todo = todo;

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getAmountR() {
        return amountR;
    }

    public void setAmountR(String amountR) {
        this.amountR = amountR;
    }

    public int getTodo() {
        return todo;
    }

    public void setTodo(int todo) {
        this.todo = todo;
    }
}
