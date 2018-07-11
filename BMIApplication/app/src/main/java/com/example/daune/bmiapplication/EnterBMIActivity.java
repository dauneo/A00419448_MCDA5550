package com.example.daune.bmiapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.text.DecimalFormat;

public class EnterBMIActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_bmi);
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


    }
}
