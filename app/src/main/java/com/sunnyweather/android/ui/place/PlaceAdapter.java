package com.sunnyweather.android.ui.place;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.sunnyweather.android.R;
import com.sunnyweather.android.logic.model.Place;

import java.util.List;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.ViewHolder> {

    private Fragment fragment;
    private List<Place> placeList;

    public PlaceAdapter(Fragment fragment, List<Place> placeList) {
        this.fragment = fragment;
        this.placeList = placeList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Place place = placeList.get(position);
        holder.getPlaceName().setText(place.getName());
        holder.getPlaceAddress().setText(place.getAddress());
    }


    @Override
    public int getItemCount() {
        return placeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private View view;
        private TextView placeName;
        private TextView placeAddress;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
            this.placeName = view.findViewById(R.id.placeName);
            this.placeAddress = view.findViewById(R.id.placeAddress);
        }

        public TextView getPlaceName() {
            return placeName;
        }

        public TextView getPlaceAddress() {
            return placeAddress;
        }
    }
}
