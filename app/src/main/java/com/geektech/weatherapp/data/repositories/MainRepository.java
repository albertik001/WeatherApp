package com.geektech.weatherapp.data.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.geektech.weatherapp.common.ResourceWeather;
import com.geektech.weatherapp.data.local.db.daos.ResponseDao;
import com.geektech.weatherapp.data.remote.apiservices.WeatherApi;
import com.geektech.weatherapp.data.remote.dto.MainResponse;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {
    private WeatherApi api;
    private ResponseDao responseDao;
    private static final String API_KEY = "f4094b2bcf523dcedd8d8ece45efc459";
    private static final String UNITS = "metric";


    @Inject
    public MainRepository(WeatherApi api, ResponseDao responseDao) {
        this.api = api;
        this.responseDao = responseDao;
    }


    public MutableLiveData<ResourceWeather<MainResponse>> fetchWeather(String city) {
        MutableLiveData<ResourceWeather<MainResponse>> liveData = new MutableLiveData<>();
        liveData.postValue(ResourceWeather.loading());
        api.fetchWeather(city, API_KEY, UNITS).enqueue(new Callback<MainResponse>() {
            @Override
            public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.setValue(ResourceWeather.success(response.body()));
                    responseDao.insert(response.body());
                } else {
                    liveData.setValue(ResourceWeather.error(response.message(), null));
                }
            }

            @Override
            public void onFailure(Call<MainResponse> call, Throwable t) {
                liveData.setValue(ResourceWeather.error(t.getLocalizedMessage(), null));
            }
        });
        return liveData;
    }


    public LiveData<List<MainResponse>> getWeather() {
        return responseDao.getAll();
    }

}
