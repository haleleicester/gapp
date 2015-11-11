package com.example.gapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.gapp.model.Guide;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Jayne on 11/11/2015.
 */

//reading an existing database http://blog.reigndesign.com/blog/using-your-own-sqlite-database-in-android-applications/
//making a singleton https://guides.codepath.com/android/Local-Databases-with-SQLiteOpenHelper
    /**
     * Created by Jayne on 10/11/2015.
     */
    public class GuideDataBaseHelper extends SQLiteOpenHelper {

        //The Android's default system path of your application database.
        private static String DB_PATH = "/data/data/com.example.gapp/databases/";

        //private static String DB_NAME = "db_guide.sql";

        private static final String DB_NAME = "SampleDb";
        private static final int DB_VERSION = 1;

        private SQLiteDatabase myDataBase;

        private final Context myContext;

        // Table Names
        private static final String TABLE_GUIDE = "guide";
        private static final String TABLE_GUIDE_TIP = "guide_tips";
        private static final String TABLE_TIP = "tips";

        // Guide Table Columns
        private static final String KEY_GUIDE_ID = "guide_id";
        private static final String KEY_GUIDE_TITLE = "title";
        private static final String KEY_GUIDE_EXP_AMOUNT = "expected_amount";
        private static final String KEY_GUIDE_HOW_WORKS = "how_it_works";

        // guide_tips Table Columns
        private static final String KEY_LINK_GUIDE_ID = "guide_id";
        private static final String KEY_LINK_TIP_ID = "tip_id";

        // tips Table Columns
        private static final String KEY_TIPS_ID = "tip_id";
        private static final String KEY_TIP = "tip";

        /**
         * Constructor
         * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
         * @param context
         */
        public GuideDataBaseHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
            this.myContext = context;
        }

        /**
         * Creates a empty database on the system and rewrites it with your own database.
         * */
        public void createDataBase() throws IOException{
/*
            boolean dbExist = checkDataBase();

            if(dbExist){
                //do nothing - database already exist
            }else{

                //By calling this method and empty database will be created into the default system path
                //of your application so we are gonna be able to overwrite that database with our database.
                this.getReadableDatabase();

                try {

                    copyDataBase();

                } catch (IOException e) {

                    throw new Error("Error copying database");

                }
            }
*/
        }

        /**
         * Check if the database already exist to avoid re-copying the file each time you open the application.
         * @return true if it exists, false if it doesn't
         */
        private boolean checkDataBase(){

            SQLiteDatabase checkDB = null;

            try{
                String myPath = DB_PATH + DB_NAME;
                checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

            }catch(SQLiteException e){

                //database does't exist yet.

            }

            if(checkDB != null){

                checkDB.close();

            }

            return checkDB != null ? true : false;
        }

        /**
         * Copies your database from your local assets-folder to the just created empty database in the
         * system folder, from where it can be accessed and handled.
         * This is done by transfering bytestream.
         * */
        private void copyDataBase() throws IOException{

            //Open your local db as the input stream
            InputStream myInput = myContext.getAssets().open(DB_NAME);

            // Path to the just created empty db
            String outFileName = DB_PATH + DB_NAME;

            //Open the empty db as the output stream
            OutputStream myOutput = new FileOutputStream(outFileName);

            //transfer bytes from the inputfile to the outputfile
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer))>0){
                myOutput.write(buffer, 0, length);
            }

            //Close the streams
            myOutput.flush();
            myOutput.close();
            myInput.close();

        }

        public void openDataBase() throws SQLException {

            //Open the database
            String myPath = DB_PATH + DB_NAME;
            myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        }

        @Override
        public synchronized void close() {

            if(myDataBase != null)
                myDataBase.close();

            super.close();

        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            onUpgrade(db, 0, DB_VERSION);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            switch (oldVersion) {
                case 0:
                    // create version 1
                    //guide table
                    db.execSQL("CREATE TABLE guide (guide_id TEXT PRIMARY KEY, title TEXT, expected_amount INTEGER, how_it_works TEXT);");
                    db.execSQL("INSERT INTO guide VALUES ('G1', 'Car Boot Sale', '£200 to £500', 'Selling items is a good way to raise money and can be repeated several times as people are getting something for their money as well as contributing to your volunteering overseas. If you are selling items to the genral public it also means that you are collecting money from a wider group  and are not asking the same peopel to contribute all the time.bCollect the items to be sold which can be donated, where you get the full price, or bought, were you get the profit in the sales. Remember to set the price at the appropriate level for the location to get as much as possible but not appear to high that they go to other stalls.');");
                    //tips table
                    db.execSQL("CREATE TABLE tips (tip_id TEXT PRIMARY KEY,tip TEXT);");
                    db.execSQL("INSERT INTO `tips` VALUES ('T1','Venue - select a good venue for the numbers expected which doesn?t cost too much. It may be free if they provide a bar.');");
                    db.execSQL("INSERT INTO `tips` VALUES ('T2','Advertise the event in advance. People need to know the date and time in advance to come along.');");
                    db.execSQL("INSERT INTO `tips` VALUES ('T3','Remember to allow fro all the cost when setting prices e.g. the cost of the stall.');");
                    db.execSQL("INSERT INTO `tips` VALUES ('T4','Make sure people know you are raising money for a charitable cause, they are more likely to pay more.');");
                    db.execSQL("INSERT INTO `tips` VALUES ('T5','Presentation counts. The items need to be sen to be sold.');");
                    db.execSQL("INSERT INTO `tips` VALUES ('T6','Have a float with plenty of change for the day so you don''t loss sales.');");

                    //guide_tips link table
                    db.execSQL("CREATE TABLE guide_tips (guide_id TEXT,tip_id TEXT, PRIMARY KEY (guide_id, tip_id));");
                    db.execSQL("INSERT INTO `guide_tips` VALUES ('G1','T2');");
                    db.execSQL("INSERT INTO `guide_tips` VALUES ('G1','T3');");
                    db.execSQL("INSERT INTO `guide_tips` VALUES('G1', 'T4');");
                    db.execSQL("INSERT INTO `guide_tips` VALUES ('G1','T5');");
                    db.execSQL("INSERT INTO `guide_tips` VALUES ('G1','T6');");


                case 1:
                    // upgrade from 1 -> 2

                case 2:
                    // upgrade from 2 -> 3

                    break;

                default:
                    throw new IllegalStateException("No upgrade defined for " + oldVersion + " -> " + newVersion);
            }
        }

        // Add your public helper methods to access and get content from the database.
        // You could return cursors by doing "return myDataBase.query(....)" so it'd be easy
        // to you to create adapters for your views.

        public Guide getGuide(String guideID) {
            Guide newGuide=new Guide();
            String GUIDE_SELECT_QUERY ="Select * FROM " + TABLE_GUIDE + " WHERE " + KEY_GUIDE_ID + " = ?";
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery(GUIDE_SELECT_QUERY, new String[]{guideID});

            try {
                if (cursor.moveToFirst()) {
                    List<String> tips = new ArrayList<>();//vector of tips for this guide
                    String TIP_SELECT_QUERY ="Select tips.tip FROM " + TABLE_TIP+ " INNER JOIN "+ TABLE_GUIDE_TIP+
                            " ON "+ TABLE_TIP+"."+KEY_TIPS_ID +" = "+ TABLE_GUIDE_TIP+"."+KEY_LINK_TIP_ID + " WHERE " + KEY_LINK_GUIDE_ID + " = ?";

                    Log.d("gapp_debug1", TIP_SELECT_QUERY + "");
                    Cursor cursorTips = db.rawQuery(TIP_SELECT_QUERY, new String[]{guideID});
                    if (cursorTips.moveToFirst()) {
                        do {   //fill up the vector of tips
                            tips.add(cursorTips.getString(cursorTips.getColumnIndex(KEY_TIP)));
                            //Log.d("gapp_debug", Arrays.toString(cursorTips.getColumnNames()));
                            //Log.d("gapp_debug", "Tips is now... " + tips.size());
                        } while (cursorTips.moveToNext());
                    }
                    newGuide.makeGuide(
                            cursor.getString(cursor.getColumnIndex(KEY_GUIDE_ID)),
                            cursor.getString(cursor.getColumnIndex(KEY_GUIDE_TITLE)),
                            cursor.getString(cursor.getColumnIndex(KEY_GUIDE_EXP_AMOUNT)),
                            cursor.getString(cursor.getColumnIndex(KEY_GUIDE_HOW_WORKS)),
                            tips
                    );
                    //close the cursor tips
                    if (cursorTips != null && !cursorTips.isClosed()) {
                        cursorTips.close();
                    }
                }
            } catch (Exception e) {
                //Log.d(TAG, "Error while trying to get posts from database");
            } finally {
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }

            }
            return newGuide;
        }


}