package com.example.tana5915.drsroommonitor;

import android.util.Log;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Document {
    // JSONObject obj = new JSONObject("assets/data.json");
    //  "Aidan","11/18/2018","12:30:00 PM","1:00:00 PM","DRS"
    ArrayList<Day> dayList;
    ArrayList<Meeting> meetingArrayLIst;
    String TAG;
    Calendar calendar;
    int dayOfMonth;

    public Document() {
       calendar = Calendar.getInstance();
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        //Log.d("DocumentDay", ""+dayOfMonth)  ;


    //    Log.d("Document", dayOfMonthStr);
        Date d = new Date();

        dayList = new ArrayList<>();
        meetingArrayLIst = new ArrayList<>();
        for (int i = 0; i < 15; i++) {

            dayList.add(new Day());
        }
    }
//21-Jan-2019


    public void fill() {
         for (int i = 0; i<meetingArrayLIst.size();i++)
         {

         }
        int index = -4;
        int meetingIndex=0;
        for (Day day : dayList) {
            calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH,index );
            dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

            Log.d("DocumentDay", ""+dayOfMonth)  ;
                for (int i = meetingIndex; i <meetingArrayLIst.size(); i++) {
                    Log.d("ForMeetingDay", meetingArrayLIst.get(i).getsDay())   ;                   

                    if(Integer.parseInt(meetingArrayLIst.get(i).getsDay())==dayOfMonth)
                        {
                                 day.addMeeting(meetingArrayLIst.get(i));
                                 meetingIndex++;

                        }
                       else
                        {
                            index++;
                            break;
                        }

                              //mock
                }

        }





    }
    private static String getCurrentDate() throws ParseException{
        String d1 = new Date().toString();
        Date d = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).parse(d1);
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        String monthName = new SimpleDateFormat("MMMM").format(cal.getTime());
        String day = ""+d.getDay();
        String year = ""+d.getYear();
        Log.d("Document",day+"-"+monthName+"-"+year );
        return day+"-"+monthName+"-"+year;
    }

        public ArrayList<Day> getDayList ()
        {
            return dayList;
        }


        public void createMeeting (String organizer, String sDate, String sTime, String eTime, String subject){

            Meeting m = new Meeting(organizer, sDate, sTime, eTime, subject);
            meetingArrayLIst.add(m);
            Log.d("MeetingDay", m.getsDay())     ;

        }




}
