package com.sunnyweather.android.ui.place;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sunnyweather.android.R;
import com.sunnyweather.android.logic.model.place.Place;
import com.sunnyweather.android.ui.weather.WeatherActivity;

import java.util.List;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.ViewHolder> {

    private PlaceFragment fragment;
    private List<Place> placeList;

    public PlaceAdapter(PlaceFragment fragment, List<Place> placeList) {
        this.fragment = fragment;
        this.placeList = placeList;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Place place = placeList.get(position);
                fragment.getViewModel().savePlace(place);
                Intent intent = new Intent(parent.getContext(), WeatherActivity.class);
                intent.putExtra("location_lng",place.getLocation().getLng());
                intent.putExtra("location_lat",place.getLocation().getLat());
                intent.putExtra("place_name",place.getName());
                fragment.startActivity(intent);
                if(fragment.getActivity() != null){
                    fragment.getActivity().finish();
                }
            }
        });
        return holder;
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
