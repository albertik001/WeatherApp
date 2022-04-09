package com.geektech.weatherapp.data.local.db.room;

import android.content.Context;

import androidx.room.Room;

import com.geektech.weatherapp.data.local.db.daos.ResponseDao;
import com.geektech.weatherapp.data.local.db.database.AppDatabase;

import javax.inject.Inject;

public class RoomClient {
    private static AppDatabase appDatabase;


    public RoomClient() {

    }

    public static AppDatabase provideDatabase(Context context) {
        return appDatabase = Room.databaseBuilder(context,
                AppDatabase.class, "database").allowMainThreadQueries().fallbackToDestructiveMigration().build();

    }

    public static ResponseDao provideDao(AppDatabase appDatabase) {
        return appDatabase.responseDao();
    }
}
