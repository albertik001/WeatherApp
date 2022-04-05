package com.geektech.weatherapp.ui.WeatherFragments;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.geektech.weatherapp.common.ResourceWeather;
import com.geektech.weatherapp.data.models.MainResponse;
import com.geektech.weatherapp.data.repositories.MainRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class WeatherViewModel extends ViewModel {

    public LiveData<ResourceWeather<MainResponse>> liveData;
    private MainRepository repository;

    @Inject
    public WeatherViewModel(MainRepository repository) {
        this.repository = repository;
    }

    public void getWeather(String city){
        liveData = repository.getWeather(city);
    }

}
