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
    public Document()
    {
        dayList = new ArrayList<>();
        for (int i = 0; i<15; i++)
        {
            dayList.add(new Day());
        }
    }

    public static void writeToFile(Context context, String fileName, String str){
        try{
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            fos.write(str.getBytes(),0,str.length());
            fos.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void tempFill(){
        String organizer = "Aidan";
        String sDate = "11/18/2000";
        String sTime = "12:30:00 PM";
        String eTime = "1:00:00 PM";
        String subject = "DRS";

        for (Day d: dayList) {
        for (int i =0; i<8;i++)
        {
          d.addMeeting(createMeeting(organizer, sDate, sTime, eTime, subject));
        }

        }


    }
    public ArrayList<Day> getDayList()
    {
        return dayList;
    }




    public Meeting createMeeting(String organizer, String sDate, String sTime, String eTime, String subject){

        Meeting m = new Meeting(organizer, sDate, sTime, eTime, subject);
        return m;
    }





}
