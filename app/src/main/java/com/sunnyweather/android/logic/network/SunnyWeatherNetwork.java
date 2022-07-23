package com.sunnyweather.android.logic.network;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sunnyweather.android.logic.model.Place;
import com.sunnyweather.android.logic.model.PlaceResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SunnyWeatherNetwork {
    private static PlaceService placeService = ServiceCreator.create(PlaceService.class);
    public static LiveData<PlaceResponse> searchPlaces(String query){
        final MutableLiveData<PlaceResponse> placeResponseLivaData = new MutableLiveData<>();
        placeService.searchPlaces(query).enqueue(new Callback<PlaceResponse>() {
            @Override
            public void onResponse(Call<PlaceResponse> call, Response<PlaceResponse> response) {
                PlaceResponse body = response.body();
                if(body != null){
                    placeResponseLivaData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<PlaceResponse> call, Throwable t) {
                Log.d("SunnyWeatherNetwork","can not get response");
            }
        });
        return placeResponseLivaData;
    }

}
