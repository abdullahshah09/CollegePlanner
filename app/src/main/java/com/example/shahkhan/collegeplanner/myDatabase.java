package com.example.shahkhan.collegeplanner;

/**
 * Created by shahkhan on 22/11/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;


public class myDatabase extends AppCompatActivity {

    // database column NAMES e.g. rowID
    private final Context context;

    private static final String MOD_TABLE = "Modules";
    private static final String ASSIGN_TABLE = "Assignments";

    private static final String MOD_ID = "module_id";
    private static final String MOD_NAME = "module_name";
    private static final String MOD_CA = "module_ca";

    private static final String ASSIGN_ID = "assignment_id";
    private static final String ASSIGN_NAME = "assignment_name";
    private static final String ASSIGN_DESC = "assignment_desc";
    private static final String ASSIGN_CA = "assignment_ca";
    private static final String ASSIGN_DONE = "assignment_done";

    private static final String DATABASE_NAME = "collegePlannerDatabase";
    private static final int DATABASE_VERSION = 2; // since it is the first version of the dB


    private static final String DATABASE_CREATE1 = "CREATE TABLE Modules ( " +
            " module_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            " module_name VARCHAR2(30), " +
            " module_ca INTEGER)";


    private static final String DATABASE_CREATE = "CREATE TABLE Assignments ( " +
            " assignment_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            " assignment_name VARCHAR2(30), " +
            " assignment_desc VARCHAR2(200), " +
            " assignment_ca INTEGER)";
    // SQL statement to create the database

    // private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    // Constructor
    public myDatabase(Context ctx)
    {
        this.context 	= ctx;
        DBHelper 		= new DatabaseHelper(context);
    }

    public myDatabase open() throws SQLException
    {
        //db     = DBHelper.getWritableDatabase();
        return this;

    }

    // nested dB helper class
    private static class DatabaseHelper extends SQLiteOpenHelper {
        //
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {


        }
            // Execute SQL to create your tables (call the execSQL method of the SQLLiteDatabase class, passing in your create table(s) SQL)


        //@Override
        //
        public void onUpgrade(SQLiteDatabase db, int oldVersion,
                              int newVersion) {
            db.execSQL(DATABASE_CREATE);

        }
    }   // end nested class


    // remainder of the Database Example methods to "use" the database
    public void close()

    {
        DBHelper.close();
    }

     // an example of a database insert.  This is for a particular database that has three columns
    public boolean insertData(String module_name, Integer module_ca)
    {
        db = DBHelper.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put(MOD_NAME, module_name);
        initialValues.put(MOD_CA, module_ca);
        db.insert("Modules", null, initialValues);
        // put your own column/ values onto the Context Values object

        long result = db.insert(DATABASE_NAME, null, initialValues);


        if(result == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

     //an example of a custom method to query a database.

    public Cursor getAllRows() throws SQLException
    {
        // The query method from SQLLiteDatabase class has various parameters that define the query: the database table, the string of columns names to be returned and
        // the last set of parameters allow you to specify "where" conditions for the query.  In this case, there is just one "where" clause. The others are unused.
        SQLiteDatabase db = DBHelper.getWritableDatabase();
        Cursor data = db.rawQuery("select module_id, module_name from " + MOD_TABLE, null);
        return data;

    }

}// end class

