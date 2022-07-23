package com.sunnyweather.android.ui.place;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.sunnyweather.android.R;


public class PlaceFragment extends Fragment {
    private PlaceViewModel viewModel;
    private PlaceAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_place,container,false);

    }

    @SuppressLint("FragmentLiveDataObserve")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(PlaceViewModel.class);
        RecyclerView recyclerView = getView().findViewById(R.id.recyclerView);
        EditText searchPlaceEdit = getView().findViewById(R.id.searchPlaceEdit);
        ImageView bgImageView = getView().findViewById(R.id.bgImageView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new PlaceAdapter(this,viewModel.getPlaceList());
        recyclerView.setAdapter(adapter);

        searchPlaceEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String content = editable.toString();
                if (content.isEmpty()){
                    recyclerView.setVisibility(View.GONE);
                    bgImageView.setVisibility(View.VISIBLE);
                    viewModel.getPlaceList().clear();
                    adapter.notifyDataSetChanged();

                }else{
                    viewModel.searchPlaces(content);
                }
            }
        });

        viewModel.getPlaceLiveData().observe(this, places -> {
            if(places != null){
                recyclerView.setVisibility(View.VISIBLE);
                bgImageView.setVisibility(View.GONE);
                viewModel.getPlaceList().clear();
                viewModel.getPlaceList().addAll(places);
                adapter.notifyDataSetChanged();
            }else{
                Toast.makeText(getActivity(),"未能查询到任何地点",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
