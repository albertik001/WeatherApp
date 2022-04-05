package com.geektech.weatherapp.data.repositories;

import androidx.lifecycle.MutableLiveData;

import com.geektech.weatherapp.common.ResourceWeather;
import com.geektech.weatherapp.data.models.MainResponse;
import com.geektech.weatherapp.data.remote.WeatherApi;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {
    private WeatherApi api;

    @Inject
    public MainRepository(WeatherApi api) {
        this.api = api;
    }

    public MutableLiveData<ResourceWeather<MainResponse>> getWeather(String city) {
        MutableLiveData<ResourceWeather<MainResponse>> liveData = new MutableLiveData<>();
        liveData.postValue(ResourceWeather.loading());
        api.getWeather(city, "f4094b2bcf523dcedd8d8ece45efc459", "metric").enqueue(new Callback<MainResponse>() {
            @Override
            public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.setValue(ResourceWeather.success(response.body()));
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
}
