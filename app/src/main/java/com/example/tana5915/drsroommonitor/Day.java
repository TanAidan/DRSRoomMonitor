package com.example.tana5915.drsroommonitor;

import java.util.ArrayList;

public class Day {

    ArrayList<Meeting> meetingList;

    public Day(){
        meetingList = new ArrayList<>();
    }
    public void addMeeting(Meeting m) {
        meetingList.add(m);
    }
    public ArrayList<Meeting> getMeetingList(){
        return meetingList;
    }
    public void clearDay()
    {
        meetingList.clear();
    }

}
