package com.example.hotornot.model.data.local.database.models;

import android.util.Log;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.hotornot.model.data.local.database.converters.CloudsTypeConverter;
import com.example.hotornot.model.data.local.database.converters.DateTypeConverter;
import com.example.hotornot.model.data.local.database.converters.MainTypeConverter;
import com.example.hotornot.model.data.local.database.converters.WeatherTypeConverter;
import com.example.hotornot.model.data.local.database.converters.WindTypeConverter;
import com.example.hotornot.model.data.remote.models.CurrentWeatherResponse;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Entity(tableName = "current_weather")
public class CurrentWeather {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @TypeConverters(WeatherTypeConverter.class)
    @ColumnInfo(name = "weather")
    private List<Weather> weather = null;

    @TypeConverters(MainTypeConverter.class)
    @ColumnInfo(name = "main")
    private Main main;

    @TypeConverters(WindTypeConverter.class)
    @ColumnInfo(name = "wind")
    private Wind wind;

    @TypeConverters(CloudsTypeConverter.class)
    @ColumnInfo(name = "clouds")
    private Clouds clouds;

    @ColumnInfo(name = "request_time")
    private Integer requestTime;

    @ColumnInfo(name = "city_name")
    private String cityName;

    @TypeConverters(DateTypeConverter.class)
    @ColumnInfo(name = "created_on")
    private Date createdOn;


    public CurrentWeather() {
        createdOn = new Date();
    }

    public CurrentWeather(CurrentWeatherResponse currentWeatherResponse) {
        createdOn = new Date();
        this.weather = currentWeatherResponse.getWeather();
        this.main = currentWeatherResponse.getMain();
        this.wind = currentWeatherResponse.getWind();
        this.clouds = currentWeatherResponse.getClouds();
        this.requestTime = currentWeatherResponse.getRequestTime();
        this.cityName = currentWeatherResponse.getCityName();

    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
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

    public String getCurrentDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return simpleDateFormat.format(createdOn);
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
