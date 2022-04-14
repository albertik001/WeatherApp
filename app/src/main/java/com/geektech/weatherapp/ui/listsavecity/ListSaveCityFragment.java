package com.geektech.weatherapp.ui.listsavecity;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.geektech.weatherapp.base.BaseFragment;
import com.geektech.weatherapp.common.OnClick;
import com.geektech.weatherapp.data.remote.dto.MainResponse;
import com.geektech.weatherapp.databinding.FragmentListSaveSityBinding;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ListSaveCityFragment extends BaseFragment<FragmentListSaveSityBinding> implements OnClick {

    private ListSaveCityAdapter adapter;
    private List<MainResponse> list;
    private ListSaveCityViewModel viewModel;
    @Override
    public FragmentListSaveSityBinding bind() {
        return FragmentListSaveSityBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list = new ArrayList<>();
        adapter = new ListSaveCityAdapter();
        adapter.setClick(this);
        viewModel = new ViewModelProvider(requireActivity()).get(ListSaveCityViewModel.class);
    }

    @Override
    protected void setupViews() {

    }

    @Override
    protected void setupOnclick() {

    }

    @Override
    protected void setupObservers() {
        binding.recyclerCity.setAdapter(adapter);
        viewModel.localLiveData.observe(getViewLifecycleOwner(), mainResponses -> {
            adapter.setMainResponseList(mainResponses);
        });
    }

    @Override
    protected void callRequests() {
        viewModel.getWeather();
    }

    @Override
    public void click(int data) {
        Log.e("TAG", "click: " + data );
        controller.navigate(ListSaveSityFragmentDirections.actionListSaveSityFragmentToWeatherFragment( data));
    }
}