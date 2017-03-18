package com.example.mobileassignment2.teeth_wars;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    //Contacts Database
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contacts.db";
    private static final String TABLE_NAME = "contacts";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "pname";
    private static final String COLUMN_SNAME = "psname";
    private static final String COLUMN_UNAME = "puname";
    private static final String COLUMN_PASS = "ppass";
    private static final String COLUMN_AVATAR = "pavatar";
    private static final String COLUMN_COINS = "pcoins";


    public Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    SQLiteDatabase db;
    private static final String TABLE_CREATE = "create table contacts (id integer primary key not null , " +
            "pname text not null , psname text not null , puname text not null, ppass text not null, pavatar text not null, pcoins text not null);";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }

    // Create new User into Database
    public void insertContact(Contact c) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String query = "select * from contacts";
        Cursor cursor = db.rawQuery(query , null);
        int count = cursor.getCount();
        // Get Values for Columns
        values.put(COLUMN_ID , count);
        values.put(COLUMN_NAME, c.getName());
        values.put(COLUMN_SNAME, c.getSname());
        values.put(COLUMN_UNAME, c.getUname());
        values.put(COLUMN_PASS, c.getPass());
        values.put(COLUMN_AVATAR, c.getAvatar());
        values.put(COLUMN_COINS, c.getCoins());


        // Insert Values into Table
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    // Edit Avatar field
    public void editavatar(String username, String Bavatar){
        db = this.getReadableDatabase();
        String query = "update contacts set pavatar = '"+ Bavatar+ "' where puname ='" + username + "'";
        db.execSQL(query);
    }

    // Edit Coins Field
    public void editcoins(String username, String Bcoins){
        db = this.getReadableDatabase();
        String query = "update contacts set pcoins = '"+ Bcoins+ "' where puname ='" + username + "'";
        db.execSQL(query);
    }

    // Get Avatar Database
    public String getAvatar (String username){
        db = this.getReadableDatabase();
        String query = "select pavatar from " +TABLE_NAME + " where puname ='" + username + "'";
        Cursor cursor = db.rawQuery(query, null);
        String a = "";
        if(cursor.moveToFirst())
        {
            a = cursor.getString(0);
        }

        return a;
    }

    // Get Name field
    public String getName (String username){
        db = this.getReadableDatabase();
        String query = "select pname from " +TABLE_NAME + " where puname ='" + username + "'";
        Cursor cursor = db.rawQuery(query, null);
        String a = "";
        if(cursor.moveToFirst())
        {
            a = cursor.getString(0);
        }

        return a;
    }

    // Get Second Name
    public String getSName (String username){
        db = this.getReadableDatabase();
        String query = "select psname from " +TABLE_NAME + " where puname ='" + username + "'";
        Cursor cursor = db.rawQuery(query, null);
        String a = "";
        if(cursor.moveToFirst())
        {
            a = cursor.getString(0);
        }

        return a;
    }

    // Get Coin Value
    public Integer getCoins (String username){
        db = this.getReadableDatabase();
        String query = "select pcoins from " +TABLE_NAME + " where puname ='" + username + "'";
        Cursor cursor = db.rawQuery(query, null);
        int a = 0;
        if(cursor.moveToFirst())
        {
            a = cursor.getInt(0);
        }

        return a;
    }

    // Search Users Pass
    public String searchPass(String uname)
    {
        db = this.getReadableDatabase();
        String query = "select puname, ppass from "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query , null);
        String a, b;
        b = "not found";
        if(cursor.moveToFirst())
        {
            do{
                a = cursor.getString(0);

                if(a.equals(uname))
                {
                    b = cursor.getString(1);
                    break;
                }
            }
            while(cursor.moveToNext());
        }

        return b;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }


}
