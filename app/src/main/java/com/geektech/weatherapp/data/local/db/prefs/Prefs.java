package com.geektech.weatherapp.data.local.db.prefs;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {
    private final SharedPreferences preferences;

    public Prefs(Context context) {
        preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
    }

    public void saveOneState() {
        preferences.edit().putBoolean("bishkek", true).apply();
    }

    public boolean isOneState() {
        return preferences.getBoolean("bishkek", false);
    }
    public void prefsCash() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("bishkek").apply();
    }
}
