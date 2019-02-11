package com.example.tana5915.drsroommonitor;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

public class MeetingAdapter extends ArrayAdapter<Meeting> {
    Context context;
    int layoutResourceId;
    List data= null;
    int currentMeetingPos = -1;

    public MeetingAdapter(Context context, int layoutResourceId, List data)
    {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        final View renderer = super.getView(position, convertView, parent);
        if (position == currentMeetingPos)
        {
            //TODO: set the proper selection color here:
            renderer.setBackgroundResource(android.R.color.holo_orange_light);
        }
        else
        {
            renderer.setBackgroundResource(android.R.color.white);
        }
        return renderer;
    }

    public void setCurrentMeetingPos(int currentMeetingPos) {
        this.currentMeetingPos = currentMeetingPos;
        notifyDataSetChanged();
    }
}
