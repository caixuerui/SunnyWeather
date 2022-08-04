package com.sunnyweather.android.ui.weather;

import androidx.arch.core.util.Function;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import com.sunnyweather.android.logic.model.place.Location;
import com.sunnyweather.android.logic.model.weather.Weather;
import com.sunnyweather.android.logic.Repository;

public class WeatherViewModel extends ViewModel{

    private String locationLng = "";
    private String locationLat = "";
    private String placeName = "";

    public String getLocationLng() {
        return locationLng;
    }

    public void setLocationLng(String locationLng) {
        this.locationLng = locationLng;
    }

    public String getLocationLat() {
        return locationLat;
    }

    public void setLocationLat(String locationLat) {
        this.locationLat = locationLat;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    private MutableLiveData<Location> locationLiveData = new MutableLiveData<>();
    private LiveData<Weather> weatherLiveData = Transformations.switchMap(locationLiveData, new Function<Location, LiveData<Weather>>() {
        @Override
        public LiveData<Weather> apply(Location input) {
            return Repository.refreshWeather(input.getLng(),input.getLat());
        }
    });

    public void refreshWeather(String lng,String lat){
        locationLiveData.setValue(new Location(lng,lat));
    }

    public LiveData<Weather> getWeatherLiveData() {
        return weatherLiveData;
    }
}
