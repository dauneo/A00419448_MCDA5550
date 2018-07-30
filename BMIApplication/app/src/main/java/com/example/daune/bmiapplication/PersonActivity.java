package com.example.daune.bmiapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class PersonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        BMIDatabaseHelper helper = new BMIDatabaseHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        String value = getIntent().getStringExtra("email");
        String[] args = { value };

        System.out.println("email: " + value);



        Cursor cursor = db.query(helper.TABLE_NAME,new
                String[]{"NAME", "EMAIL", "PASSWORD","DATE","HEALTH_CARD_NUMB"},
                "EMAIL = ?",args,null,null,null);

        if(cursor.moveToFirst()){
            String name = cursor.getString(0);
            String email = cursor.getString(1);
            String pwd = cursor.getString(2);
            long dob = cursor.getLong(3);
            String hcrd = cursor.getString(4);

            EditText nameTxt = findViewById(R.id.nameTxt);
            nameTxt.setText(name);

            EditText emailTxt = findViewById(R.id.emailText);
            emailTxt.setText(email);

            EditText birthDate = findViewById(R.id.birthDate);

            //Date date = new Date(dob);
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
            //DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM,Locale.UK); //new SimpleDateFormat("dd/MM/yy", Locale.getDefault());

            //String dateOfBirth = df.format(date);
            birthDate.setText(df.format(new Date(dob)));

            EditText password = findViewById(R.id.password);
            password.setText(pwd);

            EditText healthCard = findViewById(R.id.healthCard);
            healthCard.setText(hcrd);
        }

        cursor.close();
        db.close();

    }

    public void onClickEnter(View view) {
        Intent intent = new Intent(this, EnterBMIActivity.class);

        EditText name =  findViewById(R.id.nameTxt);
        String nameVal = name.getText().toString();

        EditText dob =  findViewById(R.id.emailText);
        String dobVal = dob.getText().toString();

        intent.putExtra("name",nameVal);
        intent.putExtra("dob",dobVal);

        startActivity(intent);
    }

    public void onClickview(View view) {
        Intent intent = new Intent(this, BMIListActivity.class);
        startActivity(intent);
    }

    public void onClickNewUser(View view) {
        Intent intent = new Intent(this, NewPersonActivity.class);
        startActivity(intent);
    }

    public void insertToTable(View view){

        BMIDatabaseHelper helper = new BMIDatabaseHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues personValues = new ContentValues();

        EditText name =  findViewById(R.id.nameTxt);
        String nameVal = name.getText().toString();

        EditText email =  findViewById(R.id.emailText);
        String emailVal = email.getText().toString();

        EditText dob =  findViewById(R.id.birthDate);
        String dobVal = dob.getText().toString();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        try {
            Date date = sdf.parse(dobVal);
            personValues.put("DATE", date.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }

        EditText pwd =  findViewById(R.id.password);
        String pwdVal = pwd.getText().toString();

        EditText hcard =  findViewById(R.id.healthCard);
        String hcardVal = hcard.getText().toString();



        personValues.put("NAME", nameVal);
        personValues.put("EMAIL", emailVal);
        personValues.put("PASSWORD", pwdVal);
        personValues.put("HEALTH_CARD_NUMB", hcardVal);


        db.insert(BMIDatabaseHelper.TABLE_NAME,null, personValues);

        System.out.println("Record inserted");

        //return true;
    }

}
