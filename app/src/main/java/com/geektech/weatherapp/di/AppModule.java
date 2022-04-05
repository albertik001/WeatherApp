package com.geektech.weatherapp.di;

import com.geektech.weatherapp.data.models.Weather;
import com.geektech.weatherapp.data.remote.WeatherApi;
import com.geektech.weatherapp.data.repositories.MainRepository;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
@Module
@InstallIn(SingletonComponent.class)
public abstract class AppModule {

    @Provides
    public static OkHttpClient provideOkHttpClient(){
        return new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }
    @Provides
    public static Retrofit provideRetrofit(OkHttpClient client){
        return new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }
    @Provides
    @Singleton
    public static WeatherApi provideApi(Retrofit retrofit){
        return retrofit.create(WeatherApi.class);
    }
    @Provides
    @Singleton
    public static MainRepository provideMainRepository(WeatherApi api){
        return new MainRepository(api);
    }
}

