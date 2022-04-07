package com.geektech.weatherapp.ui.WeatherFragments.searchFragments;

import com.geektech.weatherapp.base.BaseFragment;
import com.geektech.weatherapp.common.OnClick;
import com.geektech.weatherapp.databinding.FragmentSearchBinding;

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