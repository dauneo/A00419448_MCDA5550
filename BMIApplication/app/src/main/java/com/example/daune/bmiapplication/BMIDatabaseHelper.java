package com.example.daune.bmiapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import java.util.Date;

class BMIDatabaseHelper extends SQLiteOpenHelper{
    private SQLiteDatabase db;
    private static final String DB_NAME = "INCLASS";
    private static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "PERSON";
    public static final String BMI_TABLE = "BMI_TABLE";
    public BMIDatabaseHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){

        db.execSQL("CREATE TABLE "+TABLE_NAME+" ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "NAME TEXT,"
                + "EMAIL TEXT,"
                + "PASSWORD TEXT,"
                + "HEALTH_CARD_NUMB TEXT,"
                + "DATE LONG);");

        Date today = new Date();
        ContentValues personValues = new ContentValues();
        personValues.put("NAME", "Daune Oliver");
        personValues.put("EMAIL", "foo@example.com");
        personValues.put("PASSWORD", "Password");
        personValues.put("HEALTH_CARD_NUMB", "1234 5678 9101");
        personValues.put("DATE", today.getTime());

        db.insert(TABLE_NAME,null, personValues);

        db.execSQL("CREATE TABLE "+BMI_TABLE+" ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "NAME TEXT,"
                + "DOB LONG,"
                + "HEIGHT REAL,"
                + "WEIGHT REAL,"
                + "BMI REAL);");



    }

    public Cursor getAllRows(SQLiteDatabase db){

        Cursor cursor = db.query(BMIDatabaseHelper.BMI_TABLE,null,
                null,null,null,null,null);

        if(cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}
