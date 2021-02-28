package com.example.readandwritefile;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Calendar;
import java.util.Date;


public class MyReceiver extends BroadcastReceiver {

    private String file="appFile.txt";
    Date currentTime = Calendar.getInstance().getTime();
    @Override
    public void onReceive(Context context, Intent intent) {
        String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
        saveMessage(context, sharedText);
        Toast.makeText(context,sharedText,Toast.LENGTH_LONG).show();
    }
    public void saveMessage(Context context, String message) {

        FileOutputStream FoutS = null;
        OutputStreamWriter outSW = null;
        message = message + " " +currentTime.toString();
        try {
            FoutS = context.openFileOutput(file, Context.MODE_APPEND);
            OutputStreamWriter myOutWriter = new OutputStreamWriter(FoutS);
            myOutWriter.append(message);
            myOutWriter.append("\n");
            myOutWriter.close();
            FoutS.close();
            File fileDir = new File (context.getFilesDir(),file);
            // Rest of try block here
            Log.e("File" ,"write");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        // 'catch' and 'finally' here
    }
}