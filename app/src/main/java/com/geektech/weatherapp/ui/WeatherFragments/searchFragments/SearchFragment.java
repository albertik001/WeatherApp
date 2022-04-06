package com.geektech.weatherapp.ui.WeatherFragments.searchFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geektech.weatherapp.R;
import com.geektech.weatherapp.base.BaseFragment;
import com.geektech.weatherapp.common.OnClick;
import com.geektech.weatherapp.data.models.Weather;
import com.geektech.weatherapp.databinding.FragmentSearchBinding;

import dagger.hilt.android.AndroidEntryPoint;

public class SearchFragment extends BaseFragment<FragmentSearchBinding> implements OnClick {

    @Override
    public FragmentSearchBinding bind() {
        return FragmentSearchBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void setupViews() {

    }

    @Override
    protected void setupOnclick() {
        binding.btnNextSearch.setOnClickListener(view -> {
            String city = binding.editTextCity.getText().toString().trim();
            controller.navigate(SearchFragmentDirections.actionSearchFragmentToWeatherFragment2(city));
        });
    }

    @Override
    protected void setupObservers() {

    }

    @Override
    protected void callRequests() {

    }

    @Override
    public void click(Object data) {

    }
}