package com.sunnyweather.android.ui.weather;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.sunnyweather.android.R;
import com.sunnyweather.android.logic.model.Sky;
import com.sunnyweather.android.logic.model.weather.DailyResponse;
import com.sunnyweather.android.logic.model.weather.RealtimeResponse;
import com.sunnyweather.android.logic.model.weather.Weather;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class WeatherActivity extends AppCompatActivity {

    private WeatherViewModel viewModel;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        viewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);
        if(viewModel.getLocationLng().isEmpty()){
            String locationLng = getIntent().getStringExtra("location_lng");
            if(!locationLng.isEmpty()){
                viewModel.setLocationLng(locationLng);
            }
        }
        if(viewModel.getLocationLat().isEmpty()){
            String locationLat = getIntent().getStringExtra("location_lat");
            if(!locationLat.isEmpty()){
                viewModel.setLocationLat(locationLat);
            }
        }
        if(viewModel.getPlaceName().isEmpty()){
            String placeName = getIntent().getStringExtra("place_name");
            if(!placeName.isEmpty()){
                viewModel.setPlaceName(placeName);
            }
        }
        viewModel.getWeatherLiveData().observe(this, new Observer<Weather>() {
            @Override
            public void onChanged(Weather weather) {
                if(weather != null){
                    showWeatherInfo(weather);
                }else{
                    Toast.makeText(getApplicationContext(),"无法成功获取天气信息",Toast.LENGTH_SHORT).show();
                }
            }
        });
        viewModel.refreshWeather(viewModel.getLocationLng(),viewModel.getLocationLat());
    }

    private void showWeatherInfo(Weather weather) {
        TextView placeName = findViewById(R.id.placeName);
        TextView currentTemp = findViewById(R.id.currentTemp);
        TextView currentSky = findViewById(R.id.currentSky);
        TextView currentAQI = findViewById(R.id.currentAQI);


        placeName.setText(viewModel.getPlaceName());
        RealtimeResponse.Realtime realtime = weather.getRealtime();
        DailyResponse.Daily daily = weather.getDaily();
        currentTemp.setText(realtime.getTemperature()+"℃");
        Sky sky = Sky.getSky(realtime.getSkycon());
        currentSky.setText(sky.getInfo());
        currentAQI.setText("空气指数"+realtime.getAirQuality().getAqi().getChn());
        RelativeLayout nowLayout = findViewById(R.id.nowLayout);
        nowLayout.setBackgroundResource(sky.getBg());

        LinearLayout forecastLayout = findViewById(R.id.forecastLayout);
        forecastLayout.removeAllViews();
        int days = daily.getSkycon().size();
        for(int i=0; i<days; i++){
            DailyResponse.Skycon skycon = daily.getSkycon().get(i);
            DailyResponse.Temperature temperature = daily.getTemperature().get(i);
            View view = LayoutInflater.from(this).inflate(R.layout.forecast_item,forecastLayout,false);
            TextView dataInfo = view.findViewById(R.id.dataInfo);
            ImageView skyIcon = view.findViewById(R.id.skyIcon);
            TextView skyInfo = view.findViewById(R.id.skyInfo);
            TextView temperatureInfo = view.findViewById(R.id.temperatureInfo);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            dataInfo.setText(simpleDateFormat.format(skycon.getDate()));
            Sky dailySky = Sky.getSky(skycon.getValue());
            skyIcon.setImageResource(dailySky.getIcon());
            skyInfo.setText(dailySky.getInfo());
            temperatureInfo.setText(temperature.getMin()+"~"+temperature.getMax()+"℃");
            forecastLayout.addView(view);
        }

        DailyResponse.LifeIndex lifeIndex = daily.getLifeIndex();
        TextView coldRiskText = findViewById(R.id.coldRiskText);
        TextView dressingText = findViewById(R.id.dressingText);
        TextView ultravioletText = findViewById(R.id.ultravioletText);
        TextView carWashingText = findViewById(R.id.carWashingText);

        coldRiskText.setText(lifeIndex.getColdRisk().get(0).getDesc());
        dressingText.setText(lifeIndex.getDressing().get(0).getDesc());
        ultravioletText.setText(lifeIndex.getUltraviolet().get(0).getDesc());
        carWashingText.setText(lifeIndex.getCarWashing().get(0).getDesc());
        View weatherLayout = findViewById(R.id.weatherLayout);
        weatherLayout.setVisibility(View.VISIBLE);
    }
}