package com.example.gapp.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.gapp.CreateEvent1Activity;
import com.example.gapp.model.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liam on 10/11/15.
 */
public class EventWriter extends SQLiteOpenHelper{

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "EventsDB";

    private static final String TABLE_EVENTS = "events";
    private static final String KEY_ID = "_id";
    private static final String KEY_NAME = "_name";
    private static final String KEY_LOC = "_location";
    private static final String KEY_DATE = "_date";
    private static final String KEY_TIME = "_time";
    private static final String KEY_CATEGORY = "_cat";
    private static final String KEY_DESC = "_desc";
    private static final String KEY_TGTAMT = "_tgtamt";
    private static final String KEY_AMTRSD = "_amtrsd";
    private static final String KEY_PTRC = "_ptrc";
    private static final String KEY_TODO = "_todolist";




    private static final String[] COLUMNS = {KEY_ID,KEY_NAME,KEY_LOC,KEY_DATE, KEY_TIME, KEY_CATEGORY,KEY_DESC,KEY_TGTAMT,KEY_AMTRSD,KEY_PTRC,KEY_TODO};

    public EventWriter(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create book table
        String CREATE_BOOK_TABLE = "CREATE TABLE events ( " +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +

                "_name TEXT," +
                "_location TEXT," +
                "_date TEXT," +
                "_time TEXT," +
                "_cat TEXT," +
                "_desc TEXT," +
                "_tgtamt TEXT," +
                "_amtrsd TEXT," +
                "_ptrc TEXT," +

                "_todolist INT)";

        // create books table
        db.execSQL(CREATE_BOOK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS events");

        // create fresh books table
        this.onCreate(db);
    }


    public void addEvent(String events_name, String events_location, String events_date,String events_time,String events_cat,String events_desc,String events_tgtamt,String events_amtrsd, int events_todo){

        Log.d("addEvents", events_name.toString());
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, events_name);
        values.put(KEY_LOC, events_location);
        values.put(KEY_DATE, events_date);
        values.put(KEY_TIME, events_time);
        values.put(KEY_CATEGORY, events_cat);
        values.put(KEY_DESC, events_desc);
        values.put(KEY_TGTAMT, events_tgtamt);
        values.put(KEY_AMTRSD, events_amtrsd);
      //  values.put(KEY_PTRC, events_ptrc);
       values.put(KEY_TODO, events_todo);

        //db.insert(TABLE_EVENTS, null, values);
        if ((db.insert(TABLE_EVENTS, null, values)) != -1) {
           // Toast.makeText(EventWriter.this.getContext(), "inserted...", Toast.LENGTH_SHORT).show();
        } else {
         //   Toast.makeText(this.getApplicationContext(), "Error..", Toast.LENGTH_LONG).show();
        }
        db.close();
    }

    public List<Event> getEvents() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM events", null);
        ArrayList<Event> events = new ArrayList<>();
        if (cursor != null)// If Cursordepot is null then do
        // nothing
        {
            if (cursor.moveToFirst()) {
                do {
                    Event e = new Event(
                            cursor.getString(cursor.getColumnIndex("_name")),
                            cursor.getString(cursor.getColumnIndex("_location")),
                            cursor.getString(cursor.getColumnIndex("_date")),
                            cursor.getString(cursor.getColumnIndex("_time")),
                            cursor.getString(cursor.getColumnIndex("_cat")),
                            cursor.getString(cursor.getColumnIndex("_desc")),
                            cursor.getString(cursor.getColumnIndex("_tgtamt")),
                            cursor.getString(cursor.getColumnIndex("_amtrsd")),
                            cursor.getInt(cursor.getColumnIndex("_todolist")));
                    e.set_id(cursor.getInt(cursor.getColumnIndex("_id")));

                    events.add(e);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }


        return events;
    }
   /* public Event getEvent(int id){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor =
                db.query(TABLE_EVENTS,
                        COLUMNS,
                        " id = ?",
                        new String[] { String.valueOf(id)},
                        null,
                        null,
                        null,
                        null);

        if (cursor != null)
            cursor.moveToFirst();

     //  Event event = new event(cursor.getString(1));

      //  return event;
return event;
    }*/

}
