package com.example.tana5915.drsroommonitor;

public class Meeting {
private String organizer, sDate, sTime, eTime, subject;

 public Meeting(String organizer, String sDate, String sTime, String eTime, String subject) {
    this.organizer = organizer;
    this.sDate = sDate;
    this.sTime = sTime;
    this.eTime = eTime;
    this.subject = subject;

}
    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public String getsDate() {
        return sDate;
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
     return sTime +"   " + organizer + "   "+ subject;

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
