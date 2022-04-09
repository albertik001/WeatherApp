package com.geektech.weatherapp.data.local.db.converters;

import androidx.room.TypeConverter;

import com.geektech.weatherapp.data.remote.dto.Clouds;
import com.geektech.weatherapp.data.remote.dto.Coord;
import com.geektech.weatherapp.data.remote.dto.Main;
import com.geektech.weatherapp.data.remote.dto.Sys;
import com.geektech.weatherapp.data.remote.dto.Weather;
import com.geektech.weatherapp.data.remote.dto.Wind;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class Converters {


    @TypeConverter
    public static String sysToJson(Sys sys) {
        return new Gson().toJson(sys);
    }

    @TypeConverter
    public static Sys jsonToSys(String value) {
        return new Gson().fromJson(value, Sys.class);
    }

    @TypeConverter
    public static String coordToJson(Coord coord) {
        return new Gson().toJson(coord);
    }

    @TypeConverter
    public static Coord jsonToCoord(String value) {
        return new Gson().fromJson(value, Coord.class);
    }

    @TypeConverter
    public static String mainToJson(Main main) {

        return new Gson().toJson(main);
    }

    @TypeConverter
    public static Main jsonToMain(String value) {
        return new Gson().fromJson(value, Main.class);
    }

    @TypeConverter
    public static String windToJson(Wind wind) {

        return new Gson().toJson(wind);
    }

    @TypeConverter
    public static Wind jsonToWind(String value) {
        return new Gson().fromJson(value, Wind.class);
    }

    @TypeConverter
    public static String cloudsToJson(Clouds clouds) {
        return new Gson().toJson(clouds);
    }

    @TypeConverter
    public static Clouds jsonToClouds(String value) {
        return new Gson().fromJson(value, Clouds.class);
    }

    @TypeConverter
    public static String jsonToList(List<Weather> list) {
        return new Gson().toJson(list);
    }

    @TypeConverter
    public static List<Weather> listToJson(String value) {
        Type listType = new TypeToken<List<Weather>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }
}
