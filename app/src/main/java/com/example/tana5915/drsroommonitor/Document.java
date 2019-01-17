package com.example.tana5915.drsroommonitor;

import android.util.Log;

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

    public Document() {
        Date d = new Date();

        dayList = new ArrayList<>();
        meetingArrayLIst = new ArrayList<>();
        for (int i = 0; i < 15; i++) {

            dayList.add(new Day());
        }
    }
//21-Jan-2019


    public void fill() {


        for (Day d : dayList) {



                for (int i = 0; i < 24; i++) {
                    try {
                        if (meetingArrayLIst.get(i).getsDate().equals(getCurrentDate()))
                            d.addMeeting(meetingArrayLIst.get(i));
                    }
                    catch(Exception e)
                    {
                        Log.d("document",e.toString());
                    }


                }

        }

           /* dayList.get(4).addMeeting(createMeeting("Todd", "1/11/19", "8:00 AM", "9:00 AM", "RedShift Software"));
            dayList.get(4).addMeeting(createMeeting("Rebekah", "1/11/19", "9:00 AM", "10:00 AM", "Apple Meeting"));
            dayList.get(4).addMeeting(createMeeting("Josh", "1/11/19", "10:00 AM", "11:00 AM", "DRS Meeting"));
            dayList.get(4).addMeeting(createMeeting("Peter", "1/11/19", "11:00 AM", "12:00 PM", "Canyon Crest Academy Meeting"));
            dayList.get(4).addMeeting(createMeeting("Aidan", "1/11/19", "12:00 PM", "1:00 PM", "Samsung Software"));
            dayList.get(4).addMeeting(createMeeting("Paymon", "1/11/19", "1:00 PM", "2:00 PM", "Microsoft Meeting"));
            dayList.get(4).addMeeting(createMeeting("Alexa", "1/11/19", "2:00 PM", "3:00 PM", "Dell Meeting"));
            dayList.get(4).addMeeting(createMeeting("Emily", "1/11/19", "3:00 PM", "4:00 PM", "Sony Meeting"));
            dayList.get(4).addMeeting(createMeeting("John", "1/11/19", "4:00 PM", "5:00 PM", " DRS Quartly Discussion"));
            dayList.get(4).addMeeting(createMeeting("Daniel", "1/11/19", "5:00 PM", "6:00 PM", "Customer Meeting with QT"));
            dayList.get(4).addMeeting(createMeeting("Jack", "1/11/19", "6:00 PM", "7:00 PM", "Secure"));
            dayList.get(4).addMeeting(createMeeting("Tom", "1/11/19", "7:00 PM", "8:00 PM", "RedShift Software"));
        dayList.get(4).addMeeting(createMeeting("Todd", "1/11/19", "8:00 PM", "9:00 PM", "RedShift Software"));
        dayList.get(4).addMeeting(createMeeting("Rebekah", "1/11/19", "9:00 AM", "10:00 PM", "Apple Meeting"));
        dayList.get(4).addMeeting(createMeeting("Josh", "1/11/19", "10:00 PM", "11:00 PM", "DRS Meeting"));
        dayList.get(4).addMeeting(createMeeting("Peter", "1/11/19", "11:00 AM", "12:00 PM", "Canyon Crest Academy Meeting"));
        dayList.get(4).addMeeting(createMeeting("Aidan", "1/11/19", "12:00 PM", "1:00 PM", "Samsung Software"));
        dayList.get(4).addMeeting(createMeeting("Paymon", "1/11/19", "1:00 PM", "2:00 PM", "Microsoft Meeting"));
        dayList.get(4).addMeeting(createMeeting("Alexa", "1/11/19", "2:00 PM", "3:00 PM", "Dell Meeting"));
        dayList.get(4).addMeeting(createMeeting("Emily", "1/11/19", "3:00 PM", "4:00 PM", "Sony Meeting"));
        dayList.get(4).addMeeting(createMeeting("John", "1/11/19", "4:00 PM", "5:00 PM", " DRS Quartly Discussion"));
        dayList.get(4).addMeeting(createMeeting("Daniel", "1/11/19", "5:00 PM", "6:00 PM", "Customer Meeting with QT"));
        dayList.get(4).addMeeting(createMeeting("Jack", "1/11/19", "6:00 PM", "7:00 PM", "Secure"));
        dayList.get(4).addMeeting(createMeeting("Tom", "1/11/19", "7:00 PM", "8:00 PM", "RedShift Software"));

*/


    }
    private static String getCurrentDate() throws ParseException{
        String d1 = new Date().toString();
        Date d = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).parse(d1);
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        String monthName = new SimpleDateFormat("MMMM").format(cal.getTime());
        String day = ""+d.getDay();
        String year = ""+d.getYear();
        return day+"-"+monthName+"-"+year;
    }
        public ArrayList<Day> getDayList ()
        {
            return dayList;
        }


        public void createMeeting (String organizer, String sDate, String sTime, String eTime, String subject){

            Meeting m = new Meeting(organizer, sDate, sTime, eTime, subject);
            meetingArrayLIst.add(m);

        }




}
