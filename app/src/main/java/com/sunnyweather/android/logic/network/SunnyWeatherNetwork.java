package com.sunnyweather.android.logic.network;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sunnyweather.android.logic.model.place.PlaceResponse;
import com.sunnyweather.android.logic.model.weather.DailyResponse;
import com.sunnyweather.android.logic.model.weather.RealtimeResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SunnyWeatherNetwork {
    private static PlaceService placeService = ServiceCreator.create(PlaceService.class);
    private static WeatherService weatherService = ServiceCreator.create(WeatherService.class);
    public static LiveData<PlaceResponse> searchPlaces(String query){
        final MutableLiveData<PlaceResponse> placeResponseLivaData = new MutableLiveData<>();
        placeService.searchPlaces(query).enqueue(new Callback<PlaceResponse>() {
            @Override
            public void onResponse(Call<PlaceResponse> call, Response<PlaceResponse> response) {
                PlaceResponse body = response.body();
                if(body != null){
                    placeResponseLivaData.setValue(body);
                }
            }

            @Override
            public void onFailure(Call<PlaceResponse> call, Throwable t) {
                Log.d("SunnyWeatherNetwork","can not get response");
            }
        });
        return placeResponseLivaData;
    }

    public static LiveData<DailyResponse> getDailyWeather(String lng,String lat){
        final MutableLiveData<DailyResponse> dailyResponseLiveData = new MutableLiveData<>();
        weatherService.getDailyWeather(lng,lat).enqueue(new Callback<DailyResponse>() {
            @Override
            public void onResponse(Call<DailyResponse> call, Response<DailyResponse> response) {
                DailyResponse body = response.body();
                if (body != null){
                    dailyResponseLiveData.setValue(body);
                }
            }

            @Override
            public void onFailure(Call<DailyResponse> call, Throwable t) {
                Log.d("SunnyWeatherNetwork","can not get response");
            }
        });
        return dailyResponseLiveData;
    }

    public static LiveData<RealtimeResponse> getRealtimeWeather(String lng,String lat){
        final MutableLiveData<RealtimeResponse> realtimeResponseLiveData = new MutableLiveData<>();
        weatherService.getRealtimeWeather(lng,lat).enqueue(new Callback<RealtimeResponse>() {
            @Override
            public void onResponse(Call<RealtimeResponse> call, Response<RealtimeResponse> response) {
                RealtimeResponse body = response.body();
                if (body != null){
                    realtimeResponseLiveData.setValue(body);
                }
            }

            @Override
            public void onFailure(Call<RealtimeResponse> call, Throwable t) {
                Log.d("SunnyWeatherNetwork","can not get response");
            }
        });
        return realtimeResponseLiveData;
    }

}
