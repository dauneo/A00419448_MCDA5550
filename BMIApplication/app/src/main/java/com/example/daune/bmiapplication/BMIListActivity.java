package com.example.daune.bmiapplication;

import android.app.ListActivity;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.text.DateFormat;
import java.util.Date;
//import java.util.Date;

public class BMIListActivity extends ListActivity {
    //Date now = new Date();

    BMIResult[] results = {new BMIResult(5.5,100),new BMIResult(4.3,156)};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_bmilist);

        ListView listBMIResults = getListView();
        ArrayAdapter<BMIResult> listAdapterr = new ArrayAdapter<BMIResult>(
                this, android.R.layout.simple_list_item_1,results
        );
        listBMIResults.setAdapter(listAdapterr);


    }

    public void onListItemClick(ListView listView, View itemView, int position, long id){
        System.out.println("Clicked on " + results[position].toString());

    }





}

