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
    private static final String[] COLUMNS = {KEY_ID,KEY_NAME,KEY_LOC,KEY_DATE};

    public EventWriter(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create book table
        String CREATE_BOOK_TABLE = "CREATE TABLE events ( " +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "_name TEXT" +
                "_location TEXT" +
                "_date TEXT" +
                "_time TEXT" +
                "_cat TEXT" +
                "_desc TEXT" +
                "_tar_amt TEXT" +
                "_amt_raised TEXT)";

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

    public void addEvent(String events_name){

        Log.d("addEvents", events_name.toString());
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, events_name);
        //values.put(KEY_LOC, events_location.get_contents());
        //values.put(KEY_DATE, events_date.get_contents());
        //db.insert(TABLE_EVENTS, null, values);
        if ((db.insert(TABLE_EVENTS, null, values)) != -1) {
            //Toast.makeText(EventWriter.this.getContext(), "inserted...", Toast.LENGTH_SHORT).show();
        } else {
           // Toast.makeText(this.getApplicationContext(), "Error..", Toast.LENGTH_LONG).show();
        }
        db.close();
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
