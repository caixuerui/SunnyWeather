package com.sunnyweather.android.logic.model.weather;

public class Weather {
    private final RealtimeResponse.Realtime realtime;
    private final DailyResponse.Daily daily;

    public Weather(RealtimeResponse.Realtime realtime, DailyResponse.Daily daily) {
        this.realtime = realtime;
        this.daily = daily;
    }

    public RealtimeResponse.Realtime getRealtime() {
        return realtime;
    }

    public DailyResponse.Daily getDaily() {
        return daily;
    }
}
