package com.sunnyweather.android.logic;

import android.util.Log;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;

import com.sunnyweather.android.logic.model.Place;
import com.sunnyweather.android.logic.model.PlaceResponse;
import com.sunnyweather.android.logic.network.SunnyWeatherNetwork;

import java.util.List;

import retrofit2.Response;

public class Repository {
    public static LiveData<List<Place>> searchPlaces(String query) {
        LiveData<PlaceResponse> placeResponseLiveData = SunnyWeatherNetwork.searchPlaces(query);
        LiveData<List<Place>> placeLiveData = Transformations.map(placeResponseLiveData, new Function<PlaceResponse,List<Place>>() {
            @Override
            public List<Place> apply(PlaceResponse placeResponse) {
                if(placeResponse.getStatus().equals("ok")){
                    return placeResponse.getPlaces();
                }else{
                    return null;
                }
            }
        });

        return placeLiveData;
    }
}
