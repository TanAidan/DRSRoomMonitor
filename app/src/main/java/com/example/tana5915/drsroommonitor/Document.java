package com.example.tana5915.drsroommonitor;

import android.content.Context;

import org.json.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Document {
    // JSONObject obj = new JSONObject("assets/data.json");
    //  "Aidan","11/18/2018","12:30:00 PM","1:00:00 PM","DRS"
    ArrayList<Day> dayList;

    public Document() {

        dayList = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            dayList.add(new Day());
        }
    }

    public static void writeToFile(Context context, String fileName, String str) {
        try {
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            fos.write(str.getBytes(), 0, str.length());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void tempFill() {
        String organizer = "Aidan";
        String sDate = "11/18/2000";
        String sTime = "12:30 PM";
        String eTime = "1:00 PM";
        String subject = "DRS";

        for (Day d : dayList) {
            if (dayList.indexOf(d) != 4) {


                for (int i = 0; i < 24; i++) {

                    d.addMeeting(createMeeting(organizer, sDate, sTime, eTime, subject));


                }
            }
        }

            dayList.get(4).addMeeting(createMeeting("Todd", "1/11/19", "8:00 AM", "9:00 AM", "RedShift Software"));
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




    }
        public ArrayList<Day> getDayList ()
        {
            return dayList;
        }


        public Meeting createMeeting (String organizer, String sDate, String sTime, String eTime, String subject){

            Meeting m = new Meeting(organizer, sDate, sTime, eTime, subject);
            return m;
        }



}
