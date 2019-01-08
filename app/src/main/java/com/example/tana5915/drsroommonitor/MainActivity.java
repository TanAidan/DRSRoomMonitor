package com.example.tana5915.drsroommonitor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    List list = new ArrayList();
    ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        TextView textViewDate = findViewById(R.id.text_view_date);
        textViewDate.setText(currentDate);


        listView = (ListView) findViewById(R.id.list_view);

        Document d = new Document();
        for (int i = 0; i<d.getDayList().size();i++) {
            list.add(d.getDayList().get(i).getMeetingList()Meeting(););
        }

        adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);

    }
}
