package com.example.tana5915.drsroommonitor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.res.AssetManager;

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
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import java.io.InputStream;
import java.util.Iterator;
public class MainActivity extends AppCompatActivity {
    String TAG ="main";

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
        readExcelFileFromAssets();
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
        d.fill();
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
    public void readExcelFileFromAssets() {
        try {
            InputStream myInput;
            // initialize asset manager
            AssetManager assetManager = getAssets();
            //  open excel sheet
            myInput = assetManager.open(".xls");
            // Create a POI File System object
            POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);
            // Create a workbook using the File System
            HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);
            // Get the first sheet from workbook
            HSSFSheet mySheet = myWorkBook.getSheetAt(0);
            // We now need something to iterate through the cells.
            Iterator<Row> rowIter = mySheet.rowIterator();
            int rowno =0;
            while (rowIter.hasNext()) {
                String subj="";
                String organ="";
                String eTime="";
                String sTime="";
                String sDate="";
                Log.e(TAG, " row no "+ rowno );
                HSSFRow myRow = (HSSFRow) rowIter.next();
                if(rowno !=0) {
                    Iterator<Cell> cellIter = myRow.cellIterator();
                    int colno =0;

                    while (cellIter.hasNext()) {
                        HSSFCell myCell = (HSSFCell) cellIter.next();
                        if (colno==0){
                            subj = myCell.toString();
                        }else if (colno==1){
                            sDate = myCell.toString();
                        }else if (colno==2){
                            sTime = myCell.toString();
                        }
                        else if (colno==4){
                            eTime = myCell.toString();
                        }
                        else if (colno==9){
                            organ = myCell.toString();
                        }

                        colno++;
                        Log.e(TAG, " Index :" + myCell.getColumnIndex() + " -- " + myCell.toString());
                    }
                    d.createMeeting(organ,sDate,sTime,eTime,subj);
                }
                rowno++;
            }
        } catch (Exception e) {
            Log.e(TAG, "error "+ e.toString());
        }
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
