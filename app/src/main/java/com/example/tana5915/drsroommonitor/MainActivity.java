package com.example.tana5915.drsroommonitor;

import android.os.CountDownTimer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.res.AssetManager;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
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
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.util.Iterator;
public class MainActivity extends AppCompatActivity {
    String TAG ="main";

    ListView listView;
    Boolean b = false;
    List list = new ArrayList();
    MeetingAdapter adapter;
    Document d;
    Calendar calendar;
    TextView textViewDate, subject, startTime, endTime, organizer;
    Meeting currentMeeting = null;
    int currentMeetingIndex=-1;
  //  String currentDate;
    int dayIndex=4;  //set to 4, 4 is the index of the 0th day

//thread example
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MainActivity","Created" );

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        d = new Document();

        readExcelFileFromAssets();

        currentMeeting = d.getCurrentMeeting();
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.abs_layout);
       // getSupportActionBar().setTitle("Meeting Room Example");
        ((TextView)getSupportActionBar().getCustomView().findViewById(R.id.tvTitle)).setText("Meeting Room Example");
        subject = findViewById(R.id.subject);
        organizer = findViewById(R.id.organizer);
        startTime = findViewById(R.id.startTime);
        endTime = findViewById(R.id.eTime);
        calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        textViewDate = findViewById(R.id.text_view_date);
        textViewDate.setText(currentDate);
        listView = (ListView) findViewById(R.id.list_view);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        d.fill();
        for (int i = 0; i<d.getDayList().get(dayIndex).getMeetingList().size();i++) {
            list.add(d.getDayList().get(dayIndex).getMeetingList().get(i));
        }

        adapter = new MeetingAdapter(MainActivity.this, android.R.layout.simple_list_item_1,list);

        listView.setAdapter(adapter);

        Log.d("DRSMainActivity","onCreate");
        setCurrentMeeting();
        //Constantly checks and updates current meeting unless a click has been placed
       Thread thread = new Thread() {

            @Override
            public void run() {
                try {
                    while (!this.isInterrupted()) {
                        Thread.sleep(250);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                              //  calendar = Calendar.getInstance();
                             //   currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
                                if(!b)
                                {
                                    setCurrentMeeting();
                                }
                                // update TextView here!
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        thread.start();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("MeetingSelected", "selected" + position);
                b = true;
                if (d.getDayList().get(dayIndex).getMeetingList().size() <= 0) {

                } else {
                    Meeting m = d.getDayList().get(dayIndex).getMeetingList().get(position);
                    String subj = "Subject: " + m.getSubject();
                    String organ = "Organizer: " + m.getOrganizer();
                    String eTime = "EndTime: " + m.geteDisplayTime();
                    String sTime = "StartTime: " + m.getsDisplayTime();
                    subject.setText(subj);
                    organizer.setText(organ);
                    endTime.setText(eTime);
                    startTime.setText(sTime);
                    new CountDownTimer(20000, 1000) {

                        public void onTick(long millisUntilFinished) {

                        }

                        public void onFinish() {
                            setCurrentMeeting();
                            b = false;
                        }

                    }.start();


                }
            }
        });

    }
    public void readExcelFileFromAssets() {
       // AssetManager assetManager = getResources().getAssets();
        InputStream inputStream = null;
        try {
            //add the calendar day 4 days ago and compare it, if it is true, then start adding stuff
            boolean add = false;
            Calendar excelCalendar;
            excelCalendar = Calendar.getInstance();
            excelCalendar.add(Calendar.DAY_OF_MONTH,-4 );
            int dayOfMonth = excelCalendar.get(Calendar.DAY_OF_MONTH);

            // initialize asset manager

            //  open excel sheet

           InputStream myInput = getAssets().open("DrsMeetingRoom.xls");
            if(myInput!=null)
            {
                Log.d(TAG, "File Found");
            }
            //File file = new File("assets\\DrsMeetingRoom.xls");
         //  FileInputStream myInput;
          // BufferedReader myInput= new BufferedReader(new InputStreamReader(activity.getAssets().open(myfile.pdf)));

           // myInput = assetManager.open("C:\\Users\\quanj3010\\Documents\\GitHub\\DRSRoomMonitor\\app\\src\\main\\java\\assets\\DrsMeetingSchedule.xls");
            // Create a POI File System object
            POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);
            // Create a workbook using the File System
            Log.d("MainActivity","Created1" );

           // XSSFWorkbook workbook = new XSSFWorkbook (file);

            HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);
            // Get the first sheet from workbook
           // XSSFSheet mySheet = workbook.getSheetAt(0);

            HSSFSheet mySheet = myWorkBook.getSheetAt(0);
            Log.d("MainActivity",mySheet.toString() );
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
                        SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm:ss");
                        HSSFCell myCell = (HSSFCell) cellIter.next();
                        if (colno==0){
                            subj = myCell.toString();
                        }else if (colno==1){
                            sDate = myCell.toString();
                        }else if (colno==2){



                                sTime = formatTime.format(myCell.getDateCellValue());


                        }
                        else if (colno==4){



                                eTime = formatTime.format(myCell.getDateCellValue());

                        }
                        else if (colno==9){
                            organ = myCell.toString();
                        }

                        colno++;
                    }
                    Log.e("MainActivity", " Index :" + rowno+ " -- " + subj+" "+sDate+" "+sTime+" "+eTime+" "+organ);
                    Meeting m = new Meeting(organ, sDate, sTime, eTime, subj);
                    if(Integer.parseInt(m.getsDay())<dayOfMonth-4|| dayOfMonth<=Integer.parseInt(m.getsDay()))
                    {
                        add = true;
                    }
                    if(add)
                    {
                        d.createMeeting(m);
                        Log.d("MainActivity","MeetingAdded" );


                    }

                }
                rowno++;
            }
            inputStream.close();
            myFileSystem.close();
            myWorkBook.close();
            Log.d("main", "ExcelFileRead");

        } catch (Exception e) {
            Log.e(TAG, "error "+ e.toString());
        }

    }
    public void setCurrentMeeting()
    {
        currentMeeting = d.getCurrentMeeting();
        if(currentMeeting!=null) {
            String subj = "Subject: " + currentMeeting.getSubject();
            String organ = "Organizer: " + currentMeeting.getOrganizer();
            String eTime = "EndTime: " + currentMeeting.geteDisplayTime();
            String sTime = "StartTime: " + currentMeeting.getsDisplayTime();
            subject.setText(subj);
            organizer.setText(organ);
            endTime.setText(eTime);
            startTime.setText(sTime);
        }
        currentMeetingIndex=d.getMeetingIndex(currentMeeting, dayIndex);
       // listView.setSelection(d.getMeetingIndex(currentMeeting));
        Log.d("CurrentMeetingIndex", "index: "+ d.getMeetingIndex(currentMeeting, dayIndex));
        adapter.setCurrentMeetingPos(d.getMeetingIndex(currentMeeting, dayIndex));
       // listView.setItemChecked(d.getMeetingIndex(currentMeeting),true);
       // Log.d("CurrentMeeting", "CurrentMeeting Inndex"+ d.getMeetingIndex(currentMeeting));
    }

    public void nextDay(View view)
    {
        adapter.setCurrentMeetingPos(d.getMeetingIndex(currentMeeting, dayIndex));


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
        adapter.setCurrentMeetingPos(d.getMeetingIndex(currentMeeting, dayIndex));


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
        if(d.getDayList().get(dayIndex).getMeetingList().size()==0)
        {
            list.add("No Meetings Today");
        }
        else {
            for (int i = 0; i < d.getDayList().get(dayIndex).getMeetingList().size(); i++) {
                list.add(d.getDayList().get(dayIndex).getMeetingList().get(i));

            }
        }

        adapter = new MeetingAdapter(MainActivity.this, android.R.layout.simple_list_item_1,list);
        adapter.setCurrentMeetingPos(currentMeetingIndex);
        //adapter.getView
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
