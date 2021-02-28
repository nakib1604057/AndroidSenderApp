package com.example.readandwritefile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import java.io.BufferedReader;
import  java.io.FileInputStream;
import java.io.FileNotFoundException;
import  java.io.FileOutputStream;
import  java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import android.util.Log;
import android.view.View;
import  android.widget.*;
public class MainActivity extends AppCompatActivity {

    private  Button writeText, readText;
    private EditText enterText;
    private TextView showText;
    private String file = "appFile.txt";
    private String fileContents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        writeText = findViewById(R.id.writeText);
        readText  = findViewById(R.id.readText);
        enterText = findViewById(R.id.enterText);
        showText  =  findViewById(R.id.showText);

        writeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fileContents = enterText.getText().toString();

                try {
                    FileOutputStream fout = openFileOutput(file, MODE_APPEND);
                    fout.write(fileContents.getBytes());
                    fout.write("\n".getBytes());
                    fout.close();
                    File fileDir = new File(getFilesDir(),file);
                    Toast.makeText(getBaseContext(),"File saved at" + fileDir,Toast.LENGTH_LONG).show();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        readText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileInputStream is;
                BufferedReader reader;
                try {
                    FileInputStream fin = openFileInput(file);
                    reader = new BufferedReader(new InputStreamReader(fin));
                    String c;
                    String temp = "";
//                    fin.read()) != -1
                    while ((c = reader.readLine()) != null){

                        temp =  temp + "\n" +c;
                        showText.setText(temp);

                    }

                    Log.e("text",temp);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


    }
}