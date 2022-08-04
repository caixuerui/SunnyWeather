package com.sunnyweather.android.ui.place;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.sunnyweather.android.logic.Repository;
import com.sunnyweather.android.logic.model.place.Place;

import java.util.LinkedList;
import java.util.List;

public class PlaceViewModel extends ViewModel {
    private MutableLiveData<String> searchLiveData = new MutableLiveData<>();
    private List<Place> placeList = new LinkedList<>();
    private LiveData<List<Place>> placeLiveData = Transformations.switchMap(searchLiveData, new Function<String, LiveData<List<Place>>>() {
        @Override
        public LiveData<List<Place>> apply(String input) {
            return Repository.searchPlaces(input);
        }
    });

    public void searchPlaces(String query){
        searchLiveData.setValue(query);
    }

    public List<Place> getPlaceList() {
        return placeList;
    }

    public LiveData<List<Place>> getPlaceLiveData() {
        return placeLiveData;
    }

    public void savePlace(Place place){
        Repository.savePlace(place);
    }

    public Place getSavedPlace(){
        return Repository.getSavedPlace();
    }

    public boolean isPlaceSaved(){
        return Repository.isPlaceSaved();
    }
}
