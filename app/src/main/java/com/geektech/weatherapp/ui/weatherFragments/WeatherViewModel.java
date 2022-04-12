package com.geektech.weatherapp.ui.weatherFragments;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.geektech.weatherapp.common.ResourceWeather;
import com.geektech.weatherapp.data.remote.dto.MainResponse;
import com.geektech.weatherapp.data.repositories.MainRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class WeatherViewModel extends ViewModel {

    public LiveData<ResourceWeather<MainResponse>> liveData;
    public LiveData<List<MainResponse>> localLiveData;
    private MainRepository repository;

    @Inject
    public WeatherViewModel(MainRepository repository) {
        this.repository = repository;
    }

    public void fetchWeather(String city) {
        liveData = repository.fetchWeather(city);
    }

    public void fetchWeatherLatLon(double lat, double lon){
        liveData = repository.fetchWeatherLatLon(lat, lon);
    }

    public void  getWeather() {
        localLiveData = repository.getWeather();
    }

}
