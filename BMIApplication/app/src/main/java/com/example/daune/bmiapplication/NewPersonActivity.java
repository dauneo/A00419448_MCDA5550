package com.example.daune.bmiapplication;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class NewPersonActivity extends Activity implements View.OnClickListener {

    EditText bdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_person);

        bdate = (EditText) findViewById(R.id.birthDate);
        bdate.setOnClickListener(this);



/*
        BMIDatabaseHelper helper = new BMIDatabaseHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        String value = getIntent().getStringExtra("email");
        String[] args = { value };



        Cursor cursor = db.query(BMIDatabaseHelper.TABLE_NAME,new
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
*/
    }

    @Override
    public void onClick(final View v) {
        // To Hide KeyBoard
        InputMethodManager i = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        i.hideSoftInputFromWindow(((EditText)v).getWindowToken(), 0);


        Calendar c = Calendar.getInstance();
        final String seperator = "-";
        int year = c.get(Calendar.YEAR);
        final int month = c.get(Calendar.MONTH);
        int day =  c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.YEAR, year);
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                cal.set(Calendar.MONTH, monthOfYear);

                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);

                //String format = new SimpleDateFormat("dd-MM-yyyy").format(cal.getTime());
                ((EditText)v).setText(df.format(cal.getTime()));

            }
        };

        DatePickerDialog d = new DatePickerDialog(this,listener,year , month, day);
        d.show();

    }


    public void onClickEnter(View view) {
        Intent intent = new Intent(this, EnterBMIActivity.class);
        startActivity(intent);
    }

    public void onClickview(View view) {
        Intent intent = new Intent(this, BMIListActivity.class);
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
