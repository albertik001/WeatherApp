package com.geektech.weatherapp.data.local.db.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.geektech.weatherapp.data.local.db.converters.Converters;
import com.geektech.weatherapp.data.local.db.daos.ResponseDao;
import com.geektech.weatherapp.data.remote.dto.MainResponse;

@Database(entities = {MainResponse.class}, version = 3, exportSchema = false)
@TypeConverters(Converters.class)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ResponseDao responseDao();

}
