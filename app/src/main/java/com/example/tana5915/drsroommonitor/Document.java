package com.example.tana5915.drsroommonitor;

import android.content.Context;

import org.json.*;

import java.io.FileOutputStream;
import java.io.IOException;

public class Document {
    JSONObject obj = new JSONObject("assets/data.json");
    public vio
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

}
