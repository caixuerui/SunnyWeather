package com.sunnyweather.android.logic;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;

import com.sunnyweather.android.logic.dao.PlaceDao;
import com.sunnyweather.android.logic.model.place.Place;
import com.sunnyweather.android.logic.model.place.PlaceResponse;
import com.sunnyweather.android.logic.model.weather.DailyResponse;
import com.sunnyweather.android.logic.model.weather.RealtimeResponse;
import com.sunnyweather.android.logic.model.weather.Weather;
import com.sunnyweather.android.logic.network.SunnyWeatherNetwork;

import java.util.List;

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


    public static LiveData<Weather> refreshWeather(String lng, String lat){
        MutableLiveData<Weather> weatherLiveData = new MutableLiveData<>();
        MediatorLiveData<Integer> liveDataMerger = new MediatorLiveData<>();
        liveDataMerger.setValue(0);
        final DailyResponse.Daily[] daily = new DailyResponse.Daily[1];
        final RealtimeResponse.Realtime[] realtime = new RealtimeResponse.Realtime[1];
        liveDataMerger.addSource(SunnyWeatherNetwork.getDailyWeather(lng, lat), new Observer<DailyResponse>() {
            @Override
            public void onChanged(DailyResponse dailyResponse) {
                if(dailyResponse.getStatus().equals("ok")){
                    daily[0] = dailyResponse.getResult().getDaily();
                    liveDataMerger.setValue(liveDataMerger.getValue()+1);
                }

            }
        });

        liveDataMerger.addSource(SunnyWeatherNetwork.getRealtimeWeather(lng, lat), new Observer<RealtimeResponse>() {
            @Override
            public void onChanged(RealtimeResponse realtimeResponse) {
                if(realtimeResponse.getStatus().equals("ok")){
                    realtime[0] = realtimeResponse.getResult().getRealtime();
                    liveDataMerger.setValue(liveDataMerger.getValue()+1);
                }

            }
        });

        liveDataMerger.observeForever(new Observer<Integer>() {
            @Override
            public void onChanged(Integer input) {
                if(input == 2){
                    weatherLiveData.setValue(new Weather(realtime[0],daily[0]));
                }
            }
        });


        /*weatherLiveData = Transformations.map(liveDataMerger, new Function<Integer, Weather>() {
            @Override
            public Weather apply(Integer input) {
                if(input == 2){
                    return new Weather(realtime[0],daily[0]);
                }
                return null;
            }
        });*/

        return weatherLiveData;
    }

    public static void savePlace(Place place){
        PlaceDao.savePlace(place);
    }

    public static Place getSavedPlace(){
        return PlaceDao.getSavedPlace();
    }

    public static boolean isPlaceSaved(){
        return PlaceDao.isPlaceSaved();
    }
}
