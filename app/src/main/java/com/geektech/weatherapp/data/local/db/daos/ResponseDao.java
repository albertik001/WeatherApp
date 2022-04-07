package com.geektech.weatherapp.data.local.db.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.geektech.weatherapp.data.remote.dto.MainResponse;

import java.util.List;

@Dao
public interface ResponseDao {

    @Insert
    void insert(MainResponse mainResponse);

    @Query("SELECT * FROM mainresponse")
    LiveData<List<MainResponse>> getAll();

}
