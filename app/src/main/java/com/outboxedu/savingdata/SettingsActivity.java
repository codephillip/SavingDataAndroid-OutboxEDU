package com.outboxedu.savingdata;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

/**
 * Created by Zed on 9/18/2015.
 */
public class SettingsActivity extends AppCompatActivity {
    Button saveButton;
    Spinner spinner;
    Toolbar toolbar;

    String[] colors = new String[]{"#008744", "#0057e7", "#ffa700", "#d62d20"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        saveButton = (Button)findViewById(R.id.button);
        spinner = (Spinner)findViewById(R.id.spinner);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Settings.saveColor(SettingsActivity.this, colors[spinner.getSelectedItemPosition()]);
                startActivity(new Intent(SettingsActivity.this, MainActivity.class));
            }
        });
    }
}
