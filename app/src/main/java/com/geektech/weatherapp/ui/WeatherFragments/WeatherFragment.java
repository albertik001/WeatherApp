package com.geektech.weatherapp.ui.WeatherFragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.geektech.weatherapp.R;
import com.geektech.weatherapp.base.BaseFragment;
import com.geektech.weatherapp.common.ResourceWeather;
import com.geektech.weatherapp.data.models.MainResponse;
import com.geektech.weatherapp.databinding.FragmentWeatherBinding;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class WeatherFragment extends BaseFragment<FragmentWeatherBinding> {

    private WeatherFragmentArgs args;
    private WeatherViewModel viewModel;

    public WeatherFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        args = WeatherFragmentArgs.fromBundle(getArguments());
        viewModel = new ViewModelProvider(requireActivity()).get(WeatherViewModel.class);
    }

    @Override
    public FragmentWeatherBinding bind() {
        return FragmentWeatherBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void setupViews() {

    }

    @Override
    protected void setupOnclick() {
        binding.nameCountry.setOnClickListener(view -> {
            controller.navigate(R.id.action_weatherFragment_to_searchFragment);
        });
    }

    @Override
    protected void setupObservers() {
        viewModel.liveData.observe(getViewLifecycleOwner(), new Observer<ResourceWeather<MainResponse>>() {
            @Override
            public void onChanged(ResourceWeather<MainResponse> mainResponseResource) {
                switch (mainResponseResource.status) {
                    case SUCCESS: {
                        binds(mainResponseResource);
                        binding.interFace.setVisibility(View.VISIBLE);
                        break;
                    }
                    case ERROR: {
                        Snackbar.make(binding.getRoot(), mainResponseResource.msg, BaseTransientBottomBar.LENGTH_LONG).show();
                        binding.interFace.setVisibility(View.INVISIBLE);
                        break;
                    }
                    case LOADING: {
                        binding.interFace.setVisibility(View.INVISIBLE);
                        break;
                    }
                }
            }

            @SuppressLint("SetTextI18n")
            private void binds(ResourceWeather<MainResponse> mainResponseResource) {
                //FORMAT DATA
                double temp = mainResponseResource.data.getMain().getTemp();
                int temps = (int) temp;
                int humidity = mainResponseResource.data.getMain().getHumidity();
                double mBar = mainResponseResource.data.getMain().getPressure();
                String mBarFormat = NumberFormat.getNumberInstance(Locale.US).format(mBar);
                double windSpeed = mainResponseResource.data.getWind().getSpeed();
                int windSpeeds = (int) windSpeed;
                long timeSunriseLong = mainResponseResource.data.getSys().getSunrise();
                long timeSunsetLong = mainResponseResource.data.getSys().getSunset();
                long timeDayTime = mainResponseResource.data.getDt();
                double tempMaxFormat = mainResponseResource.data.getMain().getTempMax();
                int tempMax = (int) tempMaxFormat;
                double tempMinFormat = mainResponseResource.data.getMain().getTempMin();
                int tempMin = (int) tempMinFormat;
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm a", Locale.ROOT);
                SimpleDateFormat simpleDateFormats = new SimpleDateFormat("HH:mm 'PM'", Locale.ROOT);
                SimpleDateFormat dayTime = new SimpleDateFormat("HH'h' m'm'", Locale.ROOT);
                SimpleDateFormat realTimeFormat = new SimpleDateFormat("EEEE, dd MMMM y | HH:mm a", Locale.ROOT);
                String realTime = String.valueOf(realTimeFormat.format(System.currentTimeMillis()));
                String timeSunrise = String.valueOf(simpleDateFormat.format(timeSunriseLong));
                String timeSunset = String.valueOf(simpleDateFormats.format(timeSunsetLong));
                String timeDaytime = String.valueOf(dayTime.format(timeDayTime));
                //END
                binding.bigTemperature.setText(String.valueOf(temps));
                binding.percentHumidity.setText(humidity + "%");
                binding.mBarNumber.setText(mBarFormat + "mBar");
                binding.kmh.setText(windSpeeds + "km/h");
                binding.timeSunrise.setText(timeSunrise);
                binding.timeSunset.setText(timeSunset);
                binding.timeDaytime.setText(timeDaytime);
                binding.smallTemperature.setText(String.valueOf(tempMax));
                binding.smallTemperatureBottom.setText(String.valueOf(tempMin));
                binding.textTime.setText(realTime);
            }
        });
    }

    @Override
    protected void callRequests() {
        viewModel.getWeather("bishkek");
    }
}