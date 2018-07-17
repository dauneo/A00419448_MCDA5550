package com.example.daune.bmiapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;


public class PersonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        BMIDatabaseHelper helper = new BMIDatabaseHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        Cursor cursor = db.query(BMIDatabaseHelper.TABLE_NAME,new String[]{"NAME", "PASSWORD","DATE"},null,null,null,null,null);

        if(cursor.moveToFirst()){
            String name = cursor.getString(0);
            String pwd = cursor.getString(1);
            String dob = cursor.getString(2);

            EditText nameTxt = findViewById(R.id.nameTxt);
            nameTxt.setText(name);

            EditText birthDate = findViewById(R.id.birthDate);
            birthDate.setText(dob);

            EditText password = findViewById(R.id.password);
            password.setText(pwd);
        }

        cursor.close();
        db.close();

    }
}
