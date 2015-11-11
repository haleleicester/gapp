package com.example.gapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.util.Pair;

import com.example.gapp.model.Guide;
import com.example.gapp.model.GuideList;

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
        private static final String TABLE_CATEGORIES="categories";

        // Guide Table Columns
        private static final String KEY_GUIDE_ID = "guide_id";
        private static final String KEY_GUIDE_TITLE = "title";
        private static final String KEY_GUIDE_EXP_AMOUNT = "amount";
        private static final String KEY_GUIDE_HOW_WORKS = "description";
        private static final String KEY_GUIDE_URL = "url";
        private static final String KEY_IMAGE = "imgName";
        private static final String KEY_CATEGORY_ID="category_id";//this should be renamed to cat_id but I am running out of time

        // guide_tips Table Columns
        private static final String KEY_LINK_GUIDE_ID = "guide_id";
        private static final String KEY_LINK_TIP_ID = "tip_id";

        // tips Table Columns
        private static final String KEY_TIPS_ID = "tip_id";
        private static final String KEY_TIP = "tip";

        // categories Table Columns
        private static final String KEY_CAT_ID = "cat_id";
        private static final String KEY_CAT_TITLE = "cat_title";
        private static final String KEY_CAT_DESC = "cat_description";

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
                    db.execSQL("CREATE TABLE guide (guide_id TEXT PRIMARY KEY, title TEXT, amount TEXT, description TEXT, url TEXT, imgName TEXT, category_id TEXT);");
                    db.execSQL("INSERT INTO guide VALUES ('G1','Car Boot Sale','£200 to £500','Selling items is a good way to raise money and can be repeated several times as people are getting something for their money as well as contributing to your volunteering overseas. If you are selling items to the general public it also means that you are collecting money from a wider group  and are not asking the same people to contribute all the time. Collect the items to be sold which can be donated, where you get the full price, or bought, were you get the profit in the sales. Remember to set the price at the appropriate level for the location to get as much as possible but not appear to high that they go to other stalls.','imgName', 'www.projecttrust.org.uk/fundraising','A1');");
                    db.execSQL("INSERT INTO guide VALUES ('G2','Holding a Quiz','£250 to £400','Provide entertainment for the between 25 to 50, or more, participating and charge an entry fee of between œ5 and œ10 per person. The venue can vary but should include some food and refreshment. The venue provider may provide a bar, and take the profits, or you can organise one or make it a BYOB.','www.projecttrust.org.uk/fundraising','imgName','A2');");
                    db.execSQL("INSERT INTO guide VALUES ('G4','Trust Applications','£500 to £1,000','Charitable Trust are numerous in the UK and have been set up to support people in a number of ways. Specific charitable aims they support include education, volunteering and specific locations both here and abroad. Your year volunteering overseas will meet a number of different criteria as will your personal situation that will enable you to apply for support.','www.projecttrust.org.uk/fundraising','imgName','A4');");

                    //tips table
                    db.execSQL("CREATE TABLE tips (tip_id TEXT PRIMARY KEY,tip TEXT);");
                    db.execSQL("INSERT INTO `tips` VALUES ('T1','Venue - select a good venue for the numbers expected which doesn?t cost too much. It may be free if they provide a bar.');");
                    db.execSQL("INSERT INTO `tips` VALUES ('T2','Advertise the event in advance. People need to know the date and time in advance to come along.');");
                    db.execSQL("INSERT INTO `tips` VALUES ('T3','Remember to allow fro all the cost when setting prices e.g. the cost of the stall.');");
                    db.execSQL("INSERT INTO `tips` VALUES ('T4','Make sure people know you are raising money for a charitable cause, they are more likely to pay more.');");
                    db.execSQL("INSERT INTO `tips` VALUES ('T5','Presentation counts. The items need to be sen to be sold.');");
                    db.execSQL("INSERT INTO `tips` VALUES ('T6','Have a float with plenty of change for the day so you don''t loss sales.');");
                    db.execSQL("INSERT INTO `tips` VALUES ('T7','Teams of 2 to 4 work well, get everyone to pay £5 to £10 depending on what food you offer.');");
                    db.execSQL("INSERT INTO `tips` VALUES ('T8','Questions - about seven sets of ten questions is best and lots can be found online, include a round about your country.');");
                    db.execSQL("INSERT INTO `tips` VALUES ('T9','Food - finger food on the night is a nice touch but include this in the ticket price..');");
                    db.execSQL("INSERT INTO `tips` VALUES ('T10','Raffle - A raffle on the night can bring in some addition money.');");
                    db.execSQL("INSERT INTO `tips` VALUES ('T11','The quiz prize ? does not have to be expensive, wine or chocolates are normal. A wooden spoon is a nice touch too.');");
                    db.execSQL("INSERT INTO `tips` VALUES ('T12','When setting prices remember to incluide all cost e.g. the cost for the stall as well as any cost to buy the items.');");
                    db.execSQL("INSERT INTO `tips` VALUES ('T13','Make sure your location has a good accessable water supply.');");
                    db.execSQL("INSERT INTO `tips` VALUES ('T14','Pick a location where cars will e.g. your local church on a Sunday.');");
                    db.execSQL("INSERT INTO `tips` VALUES ('T15','Get some help to wash the cars from friends and family.');");
                    db.execSQL("INSERT INTO `tips` VALUES ('T16','Use posters on the day to advertise and spread the word in advance.');");
                    db.execSQL("INSERT INTO `tips` VALUES ('T17','Sell them something whle they are waiting if you can e.g.coffee or a cake.');");
                    db.execSQL("INSERT INTO `tips` VALUES ('T18','Check the critreia for the trust to make sure you meet these before applying.');");
                    db.execSQL("INSERT INTO `tips` VALUES ('T19','The Trusts can take 3 to 6 months to review applications and make a decision so apply early and be patient.');");
                    db.execSQL("INSERT INTO `tips` VALUES ('T20','Not all applications will be successful, so the more you apply to the more likely you will hit your target.');");
                    db.execSQL("INSERT INTO `tips` VALUES ('T21','Thank the Trusts that support you and give them feedback on your year overseas to help volunteers in subsequent years.');");
                    db.execSQL("INSERT INTO `tips` VALUES ('T22','Proof read your application and if you can get someelse to proof read them. The quality of the application will influence the decision.');");
                    db.execSQL("INSERT INTO `tips` VALUES ('T23','Make sure to explain the benefits that your volunteer year will bring so they know why they should support you.');");

                    //guide_tips link table
                    db.execSQL("CREATE TABLE guide_tips (guide_id TEXT,tip_id TEXT, PRIMARY KEY (guide_id, tip_id));");
                    db.execSQL("INSERT INTO `guide_tips` VALUES ('G1','T2');");
                    db.execSQL("INSERT INTO `guide_tips` VALUES ('G1','T3');");
                    db.execSQL("INSERT INTO `guide_tips` VALUES ('G1','T4');");
                    db.execSQL("INSERT INTO `guide_tips` VALUES ('G1','T5');");
                    db.execSQL("INSERT INTO `guide_tips` VALUES ('G1','T6');");

                    db.execSQL("INSERT INTO `guide_tips` VALUES ('G2','T7');");
                    db.execSQL("INSERT INTO `guide_tips` VALUES ('G2','T8');");
                    db.execSQL("INSERT INTO `guide_tips` VALUES ('G2','T9');");
                    db.execSQL("INSERT INTO `guide_tips` VALUES ('G2','T10');");
                    db.execSQL("INSERT INTO `guide_tips` VALUES ('G2','T11');");
                    db.execSQL("INSERT INTO `guide_tips` VALUES ('G2','T1');");

                    db.execSQL("INSERT INTO `guide_tips` VALUES ('G3','T13');");
                    db.execSQL("INSERT INTO `guide_tips` VALUES ('G3','T14');");
                    db.execSQL("INSERT INTO `guide_tips` VALUES ('G3','T15');");
                    db.execSQL("INSERT INTO `guide_tips` VALUES ('G3','T16');");
                    db.execSQL("INSERT INTO `guide_tips` VALUES ('G3','T17');");

                    db.execSQL("INSERT INTO `guide_tips` VALUES ('G4','T18');");
                    db.execSQL("INSERT INTO `guide_tips` VALUES ('G4','T19');");
                    db.execSQL("INSERT INTO `guide_tips` VALUES ('G4','T20');");
                    db.execSQL("INSERT INTO `guide_tips` VALUES ('G4','T21');");
                    db.execSQL("INSERT INTO `guide_tips` VALUES ('G4','T22');");
                    db.execSQL("INSERT INTO `guide_tips` VALUES ('G4','T23');");

                    //categories link table with a description for each inspiration
                    db.execSQL("CREATE TABLE categories (cat_id TEXT PRIMARY KEY,cat_title TEXT, cat_description);");
                    db.execSQL("INSERT INTO categories VALUES ('A1','Sales','Organising a car boot sale, tuck shop in your school or selling craft items is a way of raising fundraising that can be repeated several times as the people supporting you are getting something in return and can be repeat customers. If you are selling at a car boot sale or church fete you are also selling to the general public so that you are asking new people to contribute to your volunteering year.');");
                    db.execSQL("INSERT INTO categories VALUES ('A2','Entertainment','Organising an entertainment event can be a good way to get people together and enjoy themselves as they fundraise for you. The money raised can be from an entry fee or activities at the event. A raffle almost always be run at the events to help boost the money raised on the night.');");
                    db.execSQL("INSERT INTO categories VALUES ('A3','Services','Helping others in order that they will help you with a donation. Also, if they know the money is going for a good cause they may give that bit extra to support you.');");
                    db.execSQL("INSERT INTO categories VALUES ('A4','Donations','General donations can be gathered from a number of sources to support the volunteering that you will undertake during your year.');");

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
            String CAT_SELECT_QUERY ="Select * FROM " + TABLE_GUIDE + " WHERE " + KEY_GUIDE_ID + " = ?";
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery(CAT_SELECT_QUERY, new String[]{guideID});

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

        public GuideList getGuideList(String categoryID) {

            GuideList newGuideList=new GuideList();
            String CAT_SELECT_QUERY ="Select * FROM " + TABLE_CATEGORIES + " WHERE " + KEY_CAT_ID + " = ?";
            Log.d("gapp_debug", CAT_SELECT_QUERY+"");
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery(CAT_SELECT_QUERY, new String[]{categoryID});

            try {
                if (cursor.moveToFirst()) {
                    List<Pair<String,String>> ideas = new ArrayList<>();//vector of ideas for this guidelist
                    String IDEA_SELECT_QUERY ="Select " +KEY_GUIDE_ID+","+ KEY_GUIDE_TITLE+  " FROM " + TABLE_GUIDE + " WHERE " + KEY_CATEGORY_ID + " = ?";

                    Log.d("gapp_debug1", IDEA_SELECT_QUERY + "");
                    Cursor cursorIdeas = db.rawQuery(IDEA_SELECT_QUERY, new String[]{categoryID});
                    if (cursorIdeas.moveToFirst()) {
                        do {   //fill up the vector of tips
                            ideas.add(new Pair<>(cursorIdeas.getString(cursorIdeas.getColumnIndex(KEY_GUIDE_ID)), cursorIdeas.getString(cursorIdeas.getColumnIndex(KEY_GUIDE_TITLE))));
                            //Log.d("gapp_debug", Arrays.toString(cursorTips.getColumnNames()));
                            Log.d("gapp_debug", "Ideas is now... " + ideas.size());
                        } while (cursorIdeas.moveToNext());
                    }
                   // (String id,String title, String description,  List<Pair<String,String>> ideas ){
                    newGuideList.makeGuideList(
                            cursor.getString(cursor.getColumnIndex(KEY_CAT_ID)),
                            cursor.getString(cursor.getColumnIndex(KEY_CAT_TITLE)),
                            cursor.getString(cursor.getColumnIndex(KEY_CAT_DESC)),
                            ideas
                    );
                    //close the cursor ideas
                    if (cursorIdeas != null && !cursorIdeas.isClosed()) {
                        cursorIdeas.close();
                    }
                }
            } catch (Exception e) {
                //Log.d(TAG, "Error while trying to get posts from database");
            } finally {
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }

            }
            return newGuideList;
        }

    }