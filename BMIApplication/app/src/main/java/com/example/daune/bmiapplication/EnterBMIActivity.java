package com.example.daune.bmiapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EnterBMIActivity extends AppCompatActivity {

    String userName;
    String dob;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_bmi);

        userName = getIntent().getStringExtra("name");
        dob = getIntent().getStringExtra("dob");
        email = getIntent().getStringExtra("email");
    }

    public void calculate(View view){
        //get height


        EditText height =  findViewById(R.id.height);
        String value = height.getText().toString();
        Double heightVal = Double.parseDouble(value);
        System.out.println("Here is the height "+heightVal);

        //Weight
        EditText weight =  findViewById(R.id.weight);
        String value2 = weight.getText().toString();
        Double weightVal = Double.parseDouble(value2);
        System.out.println("Here is the height "+weightVal);

        DecimalFormat df = new DecimalFormat("0.##");

        Double calc = (weightVal / (heightVal*heightVal));
        EditText result = findViewById(R.id.result);

        result.setText(df.format(calc));

        if (loadBMI(userName,dob, heightVal, weightVal, calc)){
            Toast.makeText(getApplicationContext(), "Data saved", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, PersonActivity.class);

            intent.putExtra("email",email);

            startActivity(intent);


        }else {
            Toast.makeText(getApplicationContext(), "Error Saving Data", Toast.LENGTH_LONG).show();

        }


    }

    Boolean loadBMI(String user, String dob,double height, double weight, double bmi){

        BMIDatabaseHelper helper = new BMIDatabaseHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues personValues = new ContentValues();


        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        try {
            Date date = sdf.parse(dob);
            personValues.put("DOB", date.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }


        personValues.put("NAME", user);
        personValues.put("HEIGHT", height);
        personValues.put("WEIGHT", weight);
        personValues.put("BMI", bmi);


        db.insert(BMIDatabaseHelper.BMI_TABLE,null, personValues);

        return true;
    }
}
