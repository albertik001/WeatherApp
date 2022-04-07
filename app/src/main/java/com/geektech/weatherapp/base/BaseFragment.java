package com.geektech.weatherapp.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewbinding.ViewBinding;

import com.geektech.weatherapp.R;

public abstract class BaseFragment<VB extends ViewBinding> extends Fragment {

    public VB binding;
    public NavController controller;

    public abstract VB bind();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = bind();
        controller = Navigation.findNavController(requireActivity(), R.id.nav_host);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViews();
        callRequests();
        setupOnclick();
        setupObservers();
    }

    protected abstract void setupViews();

    protected abstract void setupOnclick();

    protected abstract void setupObservers();

    protected abstract void callRequests();
}

