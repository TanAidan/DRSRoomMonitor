package com.example.tana5915.drsroommonitor;

import android.util.Log;

public class Meeting {
private String organizer, sDate, sTime, eTime, subject, sdisplayTime, edisplayTime;

 public Meeting(String organizer, String sDate, String sTime, String eTime, String subject) {
    this.organizer = organizer;
    this.sDate = sDate;
    this.sTime = sTime;
    this.eTime = eTime;
    this.subject = subject;
    milTime(sTime,eTime);
}
    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }


    public void setsDisplayTime(String dTime)
    {
        sdisplayTime=dTime;
    }
    public String getsDisplayTime()
    {
        return sdisplayTime;
    }


    public String geteDisplayTime()
    {
        return edisplayTime;
    }
    public void seteDisplayTime(String dTime)
    {
        edisplayTime=dTime;
    }
    public String getsDate() {
        return sDate;
    }

    public String getsDay()
    {
        String temp = "";
        for (int i=0; i<sDate.length();i++)
        {
            if(sDate.substring(i,i+1).equals("-")){
              break;
            }
            else{
                temp+=sDate.substring(i,i+1);

            }
        }
        Log.d("Meetings",temp);
        return temp;
    }

    public void setsDate(String sDate) {
        this.sDate = sDate;
    }

    public String getsTime() {
        return sTime;
    }

    public void setsTime(String sTime) {
        this.sTime = sTime;
    }

    public String geteTime() {
        return eTime;
    }

    public void setTime(String eTime) {
        this.eTime = eTime;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String toString(){
     String s = sdisplayTime +"        " + organizer + "       "+ subject;

     return s;

    }
    public void milTime(String s1, String s2){
        int i =Integer.parseInt(s1.substring(0,2));
        if(Integer.parseInt(s1.substring(0,2))>12)
        {
            i = i-12;

            setsDisplayTime(""+i+ getsTime().substring(2));
        }
        else
        {
            setsDisplayTime(getsTime());
        }
        int j =Integer.parseInt(s2.substring(0,2));
        if(Integer.parseInt(s2.substring(0,2))>12)
        {
            j = j-12;

            seteDisplayTime(""+j+geteTime().substring(2));
        }
        else{
            seteDisplayTime(geteTime());
        }

    }

    public String toJsonString(){
     return "";
    }
    public void update(String organizer, String sDate, String sTime, String eTime, String subject)
    {
        this.organizer = organizer;
        this.sDate = sDate;
        this.sTime = sTime;
        this.eTime = eTime;
        this.subject = subject;

    }



}
