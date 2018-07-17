package com.example.daune.bmiapplication;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import java.util.Date;

class BMIDatabaseHelper extends SQLiteOpenHelper{
    private static final String DB_NAME = "INCLASS";
    private static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "PERSON";
    public BMIDatabaseHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE "+TABLE_NAME+" ("
            + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "NAME TEXT,"
            + "PASSWORD TEXT,"
            + "HEALTH_CARD_NUMB TEXT,"
            + "DATE INTEGER);");

        Date today = new Date();
        ContentValues personValues = new ContentValues();
        personValues.put("NAME", "Daune Oliver");
        personValues.put("PASSWORD", "Password");
        personValues.put("HEALTH_CARD_NUMB", "1234 5678 9101");
        personValues.put("DATE", today.getTime());

        db.insert(TABLE_NAME,null, personValues);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}
