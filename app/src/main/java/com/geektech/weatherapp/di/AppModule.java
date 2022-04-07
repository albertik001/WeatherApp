package com.geektech.weatherapp.di;

import android.content.Context;

import androidx.room.Room;

import com.geektech.weatherapp.data.local.db.daos.ResponseDao;
import com.geektech.weatherapp.data.local.db.database.AppDatabase;
import com.geektech.weatherapp.data.remote.apiservices.WeatherApi;
import com.geektech.weatherapp.data.repositories.MainRepository;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public abstract class AppModule {

    @Provides
    public static OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }

    @Provides
    public static Retrofit provideRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    @Provides
    @Singleton
    public static WeatherApi provideApi(Retrofit retrofit) {
        return retrofit.create(WeatherApi.class);
    }

    @Provides
    @Singleton
    public static MainRepository provideMainRepository(WeatherApi api, ResponseDao responseDao) {
        return new MainRepository(api, responseDao);
    }

    @Singleton
    @Provides
    public static AppDatabase provideAppDatabase(@ApplicationContext Context context) {
        return Room.databaseBuilder(context,
                AppDatabase.class, "database").allowMainThreadQueries().fallbackToDestructiveMigration().build();
    }

    @Singleton
    @Provides
    public static ResponseDao provideResponseDao(AppDatabase appDatabase) {
        return appDatabase.responseDao();
    }
}

