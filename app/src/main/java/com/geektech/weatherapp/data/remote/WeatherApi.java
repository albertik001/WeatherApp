package com.geektech.weatherapp.data.remote;

import com.geektech.weatherapp.data.models.MainResponse;
import com.geektech.weatherapp.data.models.Weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {

    @GET("/data/2.5/weather")
    Call<MainResponse> getWeather(@Query("q") String name, @Query("appid") String id, @Query("units") String units);
}
