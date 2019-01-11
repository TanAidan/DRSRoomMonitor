package com.example.tana5915.drsroommonitor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
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
    Document d;
    Calendar calendar;
    TextView textViewDate, subject, startTime, endTime, organizer;

    int dayIndex=4;  //set to 4, 4 is the index of the 0th day


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        subject = findViewById(R.id.subject);
        organizer = findViewById(R.id.organizer);
        startTime = findViewById(R.id.startTime);
        endTime = findViewById(R.id.eTime);
        calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        textViewDate = findViewById(R.id.text_view_date);
        textViewDate.setText(currentDate);
        listView = (ListView) findViewById(R.id.list_view);

        d = new Document();
        d.tempFill();
        for (int i = 0; i<d.getDayList().get(dayIndex).getMeetingList().size();i++) {
            list.add(d.getDayList().get(dayIndex).getMeetingList().get(i));
        }

        adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);
        Log.d("DRSMainActivity","onCreate");



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Meeting m =  d.getDayList().get(dayIndex).getMeetingList().get(position);
                String subj = "Subject: "+m.getSubject();
                String organ = "Organizer: "+m.getOrganizer();
                String eTime = "EndTime: "+ m.geteTime();
                String sTime = "StartTime: " +m.getsTime();
                subject.setText(subj);
                organizer.setText(organ);
                endTime.setText(eTime);
                startTime.setText(sTime);

            }

        });

    }

    public void nextDay(View view)
    {


        if(dayIndex<14) {
            changeDate(true);
            dayIndex++;
        }
        else{
            //too many days
        }
        updateList();
    }
    public void backDay(View view)
    {

        if(dayIndex>0) {
            changeDate(false);
            dayIndex--;
        }
        else{
            //back too far
        }
        updateList();
    }
    public void updateList(){

        list.clear();
        for (int i = 0; i<d.getDayList().get(dayIndex).getMeetingList().size();i++) {
            list.add(d.getDayList().get(dayIndex).getMeetingList().get(i));

        }
        adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);
    }

    public void changeDate(boolean direction)
    {
        if(direction)
        {
            calendar.add(Calendar.DATE,1);
        }
        else if(!direction)
        {
            calendar.add(Calendar.DATE,-1);

        }
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        textViewDate.setText(currentDate);
    }






}
