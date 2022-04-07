package com.geektech.weatherapp.data.remote.apiservices;

import com.geektech.weatherapp.data.remote.dto.MainResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {

    @GET("/data/2.5/weather")
    Call<MainResponse> fetchWeather(@Query("q") String name, @Query("appid") String id, @Query("units") String units);
}
