package com.sunnyweather.android.logic.network;

import com.sunnyweather.android.SunnyWeatherApplication;
import com.sunnyweather.android.logic.model.place.PlaceResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PlaceService {
    @GET("v2/place?token="+ SunnyWeatherApplication.TOKEN+"&lang=zh_CN")
    Call<PlaceResponse> searchPlaces(@Query("query") String query);
}
