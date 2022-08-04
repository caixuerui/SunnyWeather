package com.sunnyweather.android.logic.dao;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.sunnyweather.android.SunnyWeatherApplication;
import com.sunnyweather.android.logic.model.place.Place;

public class PlaceDao {

    private static SharedPreferences sharedPreferences = SunnyWeatherApplication.getContext().getSharedPreferences("sunny_weather", Context.MODE_PRIVATE);
    public static void savePlace(Place place){
        sharedPreferences.edit().putString("place", new Gson().toJson(place)).apply();
    }
    public static Place getSavedPlace(){
        String placeJson = sharedPreferences.getString("place","");
        return new Gson().fromJson(placeJson,Place.class);
    }
    public static boolean isPlaceSaved(){
        return sharedPreferences.contains("place");
    }

}
