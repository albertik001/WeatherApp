package com.geektech.weatherapp.common;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ResourceWeather<T> {

    @NonNull
    public final Status status;
    public final T data;
    public final String msg;

    public ResourceWeather(@NonNull Status status, T data, String msg) {
        this.status = status;
        this.data = data;
        this.msg = msg;
    }

    public static <T> ResourceWeather<T> success(@NonNull T data) {
        return new ResourceWeather<>(Status.SUCCESS, data, null);
    }

    public static <T> ResourceWeather<T> error(String msg, @Nullable T data) {
        return new ResourceWeather<>(Status.ERROR, data, msg);
    }

    public static <T> ResourceWeather<T> loading() {
        return new ResourceWeather<>(Status.LOADING, null, null);
    }
}
