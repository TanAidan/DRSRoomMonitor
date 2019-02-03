package com.example.tana5915.drsroommonitor;

import android.util.Log;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.ZoneId;
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
        for (int i = 0; i < 15; i++) { //four days back 10 ahead plus current day = 15

            dayList.add(new Day());
        }
    }
//21-Jan-2019


    public void fill() {

        int index = -4; //Index to determine which day meetings are added too
        int meetingIndex=0; //Index to determine which Meetings have already been added and which one to add next
        for (Day day : dayList) {
            calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH,index );
            dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

            Log.d("DocumentDay", ""+dayOfMonth)  ;
                for (int i = meetingIndex; i <meetingArrayLIst.size(); i++) {
                    Log.d("ForMeetingDay", meetingArrayLIst.get(i).getsDay())   ;                   

                    if(Integer.parseInt(meetingArrayLIst.get(i).getsDay())==dayOfMonth)  //if the day ex: indexed day is equal to the meetings day, then add meeting to that day
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


    /*private static String getCurrentDate() throws ParseException{
        String d1 = new Date().toString();
        Date d = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).parse(d1);
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        String monthName = new SimpleDateFormat("MMMM").format(cal.getTime());
        String day = ""+d.getDay();
        String year = ""+d.getYear();
        Log.d("Document",day+"-"+monthName+"-"+year );
        return day+"-"+monthName+"-"+year;
    }*/

        public ArrayList<Day> getDayList ()
        {
            return dayList;
        }


        public void createMeeting (String organizer, String sDate, String sTime, String eTime, String subject){  //meeting data is read from excel and passed into this document

            Meeting m = new Meeting(organizer, sDate, sTime, eTime, subject);
            meetingArrayLIst.add(m);
            Log.d("MeetingDay", m.getsDay())     ;

        }
        // returns the current meeting
        // returns -1 if no current meeting found for that day
        public int getMeetingIndex(Meeting m)
        {
            int index=-1;
            for(int i =0; i<dayList.get(4).getMeetingList().size(); i++)
            {
                if(m.equals(dayList.get(4).getMeetingList().get(i)))
                {
                 index =i;
                }
            }
            return index;
        }

        public Meeting getCurrentMeeting()   // Changing times into 3 dates: start time, end time, and current time - used to figure out which meeting of the day are we currently on
        {
            for (Meeting m :dayList.get(4).getMeetingList())
            {
                try {
                    String sTime = m.getsTime();
                    String eTime = m.geteTime();
                    String currentTime = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
                    Date time1 = new SimpleDateFormat("HH:mm:ss").parse(sTime);
                    Date time2 = new SimpleDateFormat("HH:mm:ss").parse(eTime);
                    Date cTime = new SimpleDateFormat("HH:mm:ss").parse(currentTime);
                    Calendar c1 = Calendar.getInstance();
                    c1.setTime(time1);
                    Calendar c2 = Calendar.getInstance();
                    c2.setTime(time2);
                    Calendar c3 = Calendar.getInstance();
                    c3.setTime(cTime);

                    Date x = c3.getTime();
                    //comparing times
                    if((x.after(c1.getTime())&& x.before(c2.getTime())))
                    {
                        return m;
                    }



                }
                catch(ParseException e)
                {
                    e.printStackTrace();
                }
            }
            return null;

        }




}
