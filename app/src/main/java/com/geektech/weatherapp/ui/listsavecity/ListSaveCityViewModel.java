package com.geektech.weatherapp.ui.listsavecity;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.geektech.weatherapp.data.remote.dto.MainResponse;
import com.geektech.weatherapp.data.repositories.MainRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ListSaveCityViewModel extends ViewModel {

    private MainRepository repository;
    public LiveData<List<MainResponse>> localLiveData;

    @Inject
    public ListSaveCityViewModel(MainRepository repository) {
        this.repository = repository;
    }

    public void getWeather() {
        localLiveData = repository.getWeather();
    }
}
