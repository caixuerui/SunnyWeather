package com.sunnyweather.android.logic.model.weather;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class DailyResponse {

    private final String status;
    private final Result result;

    public DailyResponse(String status, Result result) {
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
        private final Daily daily;

        public Result(Daily daily) {
            this.daily = daily;
        }

        public Daily getDaily() {
            return daily;
        }
    }
    public class Daily {
        private final List<Temperature> temperature;
        private final List<Skycon> skycon;
        @SerializedName("life_index")
        private final LifeIndex lifeIndex;

        public Daily(List<Temperature> temperature, List<Skycon> skycon, LifeIndex lifeIndex) {
            this.temperature = temperature;
            this.skycon = skycon;
            this.lifeIndex = lifeIndex;
        }

        public List<Temperature> getTemperature() {
            return temperature;
        }

        public List<Skycon> getSkycon() {
            return skycon;
        }

        public LifeIndex getLifeIndex() {
            return lifeIndex;
        }
    }


    public class Temperature {
        private final float max;
        private final float min;

        public Temperature(float max, float min) {
            this.max = max;
            this.min = min;
        }

        public float getMax() {
            return max;
        }

        public float getMin() {
            return min;
        }
    }

    public class Skycon {
        private final String value;
        private final Date date;

        public Skycon(String value, Date date) {
            this.value = value;
            this.date = date;
        }

        public String getValue() {
            return value;
        }

        public Date getDate() {
            return date;
        }
    }

    public class LifeIndex {
        private final List<LifeDescription> coldRisk;
        private final List<LifeDescription> carWashing;
        private final List<LifeDescription> ultraviolet;
        private final List<LifeDescription> dressing;

        public LifeIndex(List<LifeDescription> coldRisk, List<LifeDescription> carWashing, List<LifeDescription> ultraviolet, List<LifeDescription> dressing) {
            this.coldRisk = coldRisk;
            this.carWashing = carWashing;
            this.ultraviolet = ultraviolet;
            this.dressing = dressing;
        }

        public List<LifeDescription> getColdRisk() {
            return coldRisk;
        }

        public List<LifeDescription> getCarWashing() {
            return carWashing;
        }

        public List<LifeDescription> getUltraviolet() {
            return ultraviolet;
        }

        public List<LifeDescription> getDressing() {
            return dressing;
        }
    }



    public class LifeDescription {
        private final String desc;

        public LifeDescription(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }
    }
}
