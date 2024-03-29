package com.sunnyweather.android.logic.model;

import com.sunnyweather.android.R;

import java.util.HashMap;
import java.util.Map;

public class Sky {
    private String info;
    private int icon;
    private int bg;
    private static Map<String,Sky> sky = new HashMap<String,Sky>(){{
        put("CLEAR_DAY",new Sky("晴", R.drawable.ic_clear_day,R.drawable.bg_clear_day));
        put("CLEAR_NIGHT",new Sky("晴",R.drawable.ic_clear_night,R.drawable.bg_clear_night));
        put("PARTLY_CLOUDY_DAY",new Sky("多云", R.drawable.ic_partly_cloud_day, R.drawable.bg_partly_cloudy_day));
        put("PARTLY_CLOUDY_NIGHT",new Sky("多云", R.drawable.ic_partly_cloud_night, R.drawable.bg_partly_cloudy_night));
        put("CLOUDY",new Sky("阴", R.drawable.ic_cloudy, R.drawable.bg_cloudy));
        put("WIND",new Sky("大风", R.drawable.ic_cloudy, R.drawable.bg_wind));
        put("LIGHT_RAIN",new Sky("小雨", R.drawable.ic_light_rain, R.drawable.bg_rain));
        put("MODERATE_RAIN",new Sky("中雨", R.drawable.ic_moderate_rain, R.drawable.bg_rain));
        put("HEAVY_RAIN",new Sky("大雨", R.drawable.ic_heavy_rain, R.drawable.bg_rain));
        put("STORM_RAIN",new Sky("暴雨", R.drawable.ic_storm_rain, R.drawable.bg_rain));
        put("THUNDER_SHOWER",new Sky("雷阵雨", R.drawable.ic_thunder_shower, R.drawable.bg_rain));
        put("SLEET",new Sky("雨夹雪", R.drawable.ic_sleet, R.drawable.bg_rain));
        put("LIGHT_SNOW",new Sky("小雪", R.drawable.ic_light_snow, R.drawable.bg_snow));
        put("MODERATE_SNOW",new Sky("中雪", R.drawable.ic_moderate_snow, R.drawable.bg_snow));
        put("HEAVY_SNOW",new Sky("大雪", R.drawable.ic_heavy_snow, R.drawable.bg_snow));
        put("STORM_SNOW",new Sky("暴雪", R.drawable.ic_heavy_snow, R.drawable.bg_snow));
        put("HAIL",new Sky("冰雹", R.drawable.ic_hail, R.drawable.bg_snow));
        put("LIGHT_HAZE",new Sky("轻度雾霾", R.drawable.ic_light_haze, R.drawable.bg_fog));
        put("MODERATE_HAZE",new Sky("中度雾霾", R.drawable.ic_moderate_haze, R.drawable.bg_fog));
        put("HEAVY_HAZE",new Sky("重度雾霾", R.drawable.ic_heavy_haze, R.drawable.bg_fog));
        put("FOG",new Sky("雾", R.drawable.ic_fog, R.drawable.bg_fog));
        put("DUST",new Sky("浮尘", R.drawable.ic_fog, R.drawable.bg_fog));
    }};

    public Sky(String info, int icon, int bg) {
        this.info = info;
        this.icon = icon;
        this.bg = bg;
    }

    public String getInfo() {
        return info;
    }

    public int getIcon() {
        return icon;
    }

    public int getBg() {
        return bg;
    }

    public static Sky getSky(String skycon) {
        return sky.get(skycon);
    }

}
