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
import java.util.List;

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
        Meeting m1 = new Meeting("Aidan", "1/7","8:00 am", "9:00 am", "top security");
        Meeting m2 = new Meeting("Aidan", "1/7","8:00 am", "9:00 am", "top security");
        Meeting m3 = new Meeting("Aidan", "1/7","8:00 am", "9:00 am", "top security");
        Meeting m4 = new Meeting("Aidan", "1/7","8:00 am", "9:00 am", "top security");
        Meeting m5 = new Meeting("Aidan", "1/7","8:00 am", "9:00 am", "top security");
        Meeting m6 = new Meeting("Aidan", "1/7","8:00 am", "9:00 am", "top security");
        Meeting m7 = new Meeting("Aidan", "1/7","8:00 am", "9:00 am", "top security");
        Meeting m8 = new Meeting("Aidan", "1/7","8:00 am", "9:00 am", "top security");
        Meeting m9 = new Meeting("Aidan", "1/7","8:00 am", "9:00 am", "top security");
        Meeting m10 = new Meeting("Aidan", "1/7","8:00 am", "9:00 am", "top security");
        Meeting m11 = new Meeting("Aidan", "1/7","8:00 am", "9:00 am", "top security");
        Meeting m12 = new Meeting("Aidan", "1/7","8:00 am", "9:00 am", "top security");
        Meeting m13 = new Meeting("Aidan", "1/7","8:00 am", "9:00 am", "top security");
        Meeting m14 = new Meeting("Aidan", "1/7","8:00 am", "9:00 am", "top security");
        Meeting m15 = new Meeting("Aidan", "1/7","8:00 am", "9:00 am", "top security");
        Meeting m16 = new Meeting("Aidan", "1/7","8:00 am", "9:00 am", "top security");
        Meeting m17 = new Meeting("Aidan", "1/7","8:00 am", "9:00 am", "top security");
        Meeting m18= new Meeting("Aidan", "1/7","8:00 am", "9:00 am", "top security");

        list.add(m1);
        list.add(m2);
        list.add(m3);
        list.add(m4);
        list.add(m5);
        list.add(m6);
        list.add(m7);
        list.add(m8);
        list.add(m9);
        list.add(m10);
        list.add(m11);
        list.add(m12);
        list.add(m13);
        list.add(m14);
        list.add(m15);
        list.add(m16);
        list.add(m17);
        list.add(m18);


        adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);

    }
}
