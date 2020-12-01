package com.example.hotornot.model.data.local.database.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.hotornot.model.data.remote.api.CurrentWeatherResponse;
import com.example.hotornot.model.data.remote.models.Clouds;
import com.example.hotornot.model.data.remote.models.Main;
import com.example.hotornot.model.data.remote.models.Weather;
import com.example.hotornot.model.data.remote.models.Wind;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "current_weather")
public class CurrentWeather {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "weather")
    private List<Weather> weather = null;

    @ColumnInfo(name = "main")
    private Main main;

    @ColumnInfo(name = "wind")
    private Wind wind;

    @ColumnInfo(name = "clouds")
    private Clouds clouds;

    @ColumnInfo(name = "request_time")
    private Integer requestTime;

    @ColumnInfo(name = "city_name")
    private String cityName;


    public CurrentWeather() {

    }

    public CurrentWeather(CurrentWeatherResponse currentWeatherResponse) {
        this.weather = currentWeatherResponse.getWeather();
        this.main = currentWeatherResponse.getMain();
        this.wind = currentWeatherResponse.getWind();
        this.clouds = currentWeatherResponse.getClouds();
        this.requestTime = currentWeatherResponse.getRequestTime();
        this.cityName = currentWeatherResponse.getCityName();
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
