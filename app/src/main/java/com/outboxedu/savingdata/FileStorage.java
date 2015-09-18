package com.outboxedu.savingdata;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Zed on 9/18/2015.
 */
public class FileStorage extends AppCompatActivity {
    Toolbar toolbar;
    Button saveButton;
    EditText textContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file_storage_activity);

        String color = Settings.getColor(this);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(Color.parseColor(color));

        setSupportActionBar(toolbar);

        textContent = (EditText)findViewById(R.id.editText);
        saveButton = (Button)findViewById(R.id.button2);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = textContent.getText().toString();
                writeTextToFile(content);
            }
        });

        readTextFromFile();
    }

    private void readTextFromFile(){
        try {
            FileInputStream input = openFileInput("my_text.txt");
            String text = "";
            int c;
            while ((c = input.read()) != -1){
                text += Character.toString((char)c);
            }
            textContent.setText(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeTextToFile(String text){
        try{
            FileOutputStream output = openFileOutput("my_text.txt", Context.MODE_PRIVATE);
            output.write(text.getBytes());
            output.close();
            Toast.makeText(this, "The file has been saved", Toast.LENGTH_LONG).show();
        }catch (FileNotFoundException ex){
            //File Not found
        }catch (IOException ex){

        }
    }
}
