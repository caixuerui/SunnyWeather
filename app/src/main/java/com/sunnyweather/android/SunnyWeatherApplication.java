package com.sunnyweather.android;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

public class SunnyWeatherApplication extends Application {




    @SuppressLint("StaticFieldLeak")
    private static Context applicationContext;
    public final static String TOKEN = "Sq9osWk0ZCe8AG5g";

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = getApplicationContext();
    }

    public static Context getContext() {
        return applicationContext;
    }
}
