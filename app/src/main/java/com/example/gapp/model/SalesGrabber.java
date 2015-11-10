package com.example.gapp.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by liam on 10/11/15.
 */
public class SalesGrabber extends SQLiteOpenHelper{

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "SalesDB";

    private static final String TABLE_SALES = "sales";
    private static final String KEY_ID = "_id";
    private static final String KEY_CONTENTS = "_contents";
    private static final String[] COLUMNS = {KEY_ID,KEY_CONTENTS};

    public SalesGrabber(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create book table
        String CREATE_BOOK_TABLE = "CREATE TABLE sales ( " +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "_contents TEXT)";

        // create books table
        db.execSQL(CREATE_BOOK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS sales");

        // create fresh books table
        this.onCreate(db);
    }

    public void addSale(Sale sale){

        Log.d("addSale", sale.toString());
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CONTENTS, sale.get_contents());

        db.insert(TABLE_SALES, null, values);

        db.close();
    }

    public Sale getSale(int id){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor =
                db.query(TABLE_SALES,
                        COLUMNS,
                        " id = ?",
                        new String[] { String.valueOf(id)},
                        null,
                        null,
                        null,
                        null);

        if (cursor != null)
            cursor.moveToFirst();

        Sale sale = new Sale(cursor.getString(1));

        return sale;

    }

}
