package com.sunnyweather.android.logic.model.weather;

import com.google.gson.annotations.SerializedName;

public class RealtimeResponse {
    private final String status;
    private final Result result;

    public RealtimeResponse(String status, Result result) {
        this.status = status;
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public Result getResult() {
        return result;
    }


    public class Result {
        private final Realtime realtime;

        public Result(Realtime realtime) {
            this.realtime = realtime;
        }

        public Realtime getRealtime() {
            return realtime;
        }
    }

    public class Realtime {
        private final String skycon;
        private final float temperature;
        @SerializedName("air_quality")
        private final AirQuality airQuality;

        public Realtime(String skycon, float temperature, AirQuality airQuality) {
            this.skycon = skycon;
            this.temperature = temperature;
            this.airQuality = airQuality;
        }

        public String getSkycon() {
            return skycon;
        }


        public float getTemperature() {
            return temperature;
        }


        public AirQuality getAirQuality() {
            return airQuality;
        }

    }

    public class AirQuality {
        private final AQI aqi;

        public AirQuality(AQI aqi) {
            this.aqi = aqi;
        }

        public AQI getAqi() {
            return aqi;
        }

    }

    public class AQI {
        private final float chn;

        public AQI(float chn) {
            this.chn = chn;
        }

        public float getChn() {
            return chn;
        }

    }
}
