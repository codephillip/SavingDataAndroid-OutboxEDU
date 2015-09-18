package com.outboxedu.savingdata;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Zed on 9/18/2015.
 */
public class Settings {
    private static final String PREFS = "mypreferences";
    private static final String COLOR_PREF = "color";

    public static String getColor(Context cxt){
        SharedPreferences prefs = cxt.getSharedPreferences(PREFS, 0);
        String color = prefs.getString(COLOR_PREF, null);
        return color;
    }

    public static void saveColor(Context cxt, String color){
        SharedPreferences prefs = cxt.getSharedPreferences(PREFS, 0);
        SharedPreferences.Editor e = prefs.edit();
        e.putString(COLOR_PREF, color);
        e.commit();
    }


}
