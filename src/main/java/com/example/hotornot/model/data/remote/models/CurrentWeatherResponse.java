package com.example.hotornot.model.data.remote.models;

import com.example.hotornot.model.data.local.database.models.Clouds;
import com.example.hotornot.model.data.local.database.models.Main;
import com.example.hotornot.model.data.local.database.models.Weather;
import com.example.hotornot.model.data.local.database.models.Wind;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CurrentWeatherResponse {

    @SerializedName("weather")
    @Expose
    private List<Weather> weather = null;
    @SerializedName("main")
    @Expose
    private Main main;
    @SerializedName("wind")
    @Expose
    private Wind wind;
    @SerializedName("clouds")
    @Expose
    private Clouds clouds;
    @SerializedName("dt")
    @Expose
    private Integer requestTime;
    @SerializedName("name")
    @Expose
    private String cityName;

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Integer getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Integer requestTime) {
        this.requestTime = requestTime;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

}
