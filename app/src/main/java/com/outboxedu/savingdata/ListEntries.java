package com.outboxedu.savingdata;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Zed on 9/18/2015.
 */
public class ListEntries extends AppCompatActivity {
    Toolbar toolbar;
    ListView listView;
    MinionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_entries_activity);

        String color = Settings.getColor(this);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(Color.parseColor(color));
        setSupportActionBar(toolbar);

        listView = (ListView)findViewById(R.id.listView);
        adapter = new MinionAdapter(this, R.layout.minion_row, getEntries());
        listView.setAdapter(adapter);
    }

    private ArrayList<Minion> getEntries(){
        ArrayList<Minion> entries = new ArrayList<>();
        SQLiteDatabase db = new DatabaseManager(this).getReadableDatabase();
        String query = "SELECT * FROM " + DatabaseManager.TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        Minion m;
        while(cursor.moveToNext()){
            m = new Minion();
            m.title = cursor.getString(1);
            m.description = cursor.getString(2);
            m.image = cursor.getString(3);

            entries.add(m);
        }

        return entries;
    }
}
