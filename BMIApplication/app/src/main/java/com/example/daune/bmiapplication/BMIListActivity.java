package com.example.daune.bmiapplication;

import android.app.ListActivity;
//import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleCursorAdapter;

import java.text.DateFormat;
import java.util.Date;
//import java.util.Date;

public class BMIListActivity extends ListActivity {
    //Date now = new Date();

    BMIResult[] results = {new BMIResult(5.5,200),new BMIResult(4.3,156)};
    SimpleCursorAdapter mAdapter;



    static final String[] PROJECTION = new String[] {"NAME","HEIGHT","WEIGHT","BMI"};

    // This is the select criteria
    static final String SELECTION = "((NAME NOTNULL) AND (NAME != '' ))";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_bmilist);

        /*
        ListView listBMIResults = getListView();
        ArrayAdapter<BMIResult> listAdapterr = new ArrayAdapter<BMIResult>(
                this, android.R.layout.simple_list_item_1,results
        );
        listBMIResults.setAdapter(listAdapterr);
        */

        populateList();


    }

    private void populateList(){

        BMIDatabaseHelper helper = new BMIDatabaseHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        Cursor cursor = helper.getAllRows(db);
        String[] fieldNames = new String[] {"NAME","HEIGHT","WEIGHT","BMI"};


        int[] toViewIds = new int[]{R.id.textViewName,R.id.textViewHeight,R.id.textViewWeight,R.id.textViewBmi};
        SimpleCursorAdapter cursorAdapter;
        cursorAdapter = new SimpleCursorAdapter(getBaseContext(),R.layout.activity_bmilist,cursor,fieldNames,toViewIds,0);
        ListView listBMIResults = getListView();
        listBMIResults.setAdapter(cursorAdapter);

    }



    public void onListItemClick(ListView listView, View itemView, int position, long id){
        System.out.println("Clicked on " + results[position].toString());

    }





}

