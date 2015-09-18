package com.outboxedu.savingdata;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by Zed on 9/18/2015.
 */
public class SQLiteStorage extends AppCompatActivity {
    Toolbar toolbar;

    Button saveEntry;
    EditText titleText;
    EditText descriptionText;

    String[] images = new String[]{
            "http://assets.cdn.moviepilot.de/assets/store/3eff010e3adc3c2c48a972766efc0b0cd2356d4958d2aca22511987a60c2/minions-character-01.jpg",
            "http://www.cartoonbrew.com/wp-content/uploads/2013/09/minions-delay.jpg",
            "http://www.rodrigomattioli.com/wp-content/uploads/2015/05/Minion-Wallpaper-1.jpg",
            "https://frubesminions.com/images/minions-prizes-home.png",
            "http://vignette2.wikia.nocookie.net/despicableme/images/7/7f/Minion-Bob-With-Teddy-Bear.jpg/revision/latest?cb=20150627163627",
            "http://www.abc.net.au/news/image/6473316-3x2-940x627.jpg",
            "http://img1.bonnesimages.com/bi/minions/minions_005.jpg",
            "http://img1.wikia.nocookie.net/__cb20131006131542/cardfight/images/4/43/Many-minions-5.jpg",
            "https://s-media-cache-ak0.pinimg.com/236x/ff/07/2a/ff072a39a2709e4fec2cd05e3763d68d.jpg",
            "http://www.minionsipsum.com/img/minion-roman.png",
            "http://i.dailymail.co.uk/i/pix/2014/11/04/1415082054818_Image_galleryImage__Minions_Trailer.JPG"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlite_storage_acitivty);
        String color = Settings.getColor(this);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(Color.parseColor(color));

        setSupportActionBar(toolbar);

        titleText = (EditText)findViewById(R.id.edt_title);
        descriptionText = (EditText)findViewById(R.id.edt_description);

        saveEntry = (Button)findViewById(R.id.btn_save_entry);
        saveEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int random = new Random().nextInt(images.length);
                saveDBEntry(titleText.getText().toString(), descriptionText.getText().toString(), images[random]);
            }
        });
    }

    private void saveDBEntry(String title, String description, String image){
        SQLiteDatabase db = new DatabaseManager(this).getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put(DatabaseManager.COLUMN_TITLE, title);
        v.put(DatabaseManager.COLUMN_DESCRIPTION, description);
        v.put(DatabaseManager.COLUMN_IMAGE, image);

        db.insert(DatabaseManager.TABLE_NAME, null, v);
        db.close();

        Toast.makeText(this, "The entry has been saved. Yalalalala", Toast.LENGTH_LONG).show();

        startActivity(new Intent(this, ListEntries.class));
    }
}
