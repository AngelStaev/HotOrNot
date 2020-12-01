package com.example.hotornot.model.data.local.database.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.hotornot.model.data.remote.models.Temp;
import com.example.hotornot.model.data.remote.models.Weather;

@Entity(tableName = "lists")
public class List {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "dt")
    private Integer dt;

    @ColumnInfo(name = "sunrise")
    private Integer sunrise;

    @ColumnInfo(name = "sunset")
    private Integer sunset;

    @ColumnInfo(name = "temp")
    private Temp temp;

    @ColumnInfo(name = "pressure")
    private Integer pressure;

    @ColumnInfo(name = "humidity")
    private Integer humidity;

    @ColumnInfo(name = "weather")
    private java.util.List<Weather> weather = null;

    @ColumnInfo(name = "speed")
    private Double speed;

    @ColumnInfo(name = "deg")
    private Integer deg;

    @ColumnInfo(name = "clouds")
    private Integer clouds;

    @ColumnInfo(name = "pop")
    private Integer pop;

    @ColumnInfo(name = "rain")
    private Double rain;

    public List() {
    }

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public Integer getSunrise() {
        return sunrise;
    }

    public void setSunrise(Integer sunrise) {
        this.sunrise = sunrise;
    }

    public Integer getSunset() {
        return sunset;
    }

    public void setSunset(Integer sunset) {
        this.sunset = sunset;
    }

    public Temp getTemp() {
        return temp;
    }

    public void setTemp(Temp temp) {
        this.temp = temp;
    }

    public Integer getPressure() {
        return pressure;
    }

    public void setPressure(Integer pressure) {
        this.pressure = pressure;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public java.util.List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(java.util.List<Weather> weather) {
        this.weather = weather;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Integer getDeg() {
        return deg;
    }

    public void setDeg(Integer deg) {
        this.deg = deg;
    }

    public Integer getClouds() {
        return clouds;
    }

    public void setClouds(Integer clouds) {
        this.clouds = clouds;
    }

    public Integer getPop() {
        return pop;
    }

    public void setPop(Integer pop) {
        this.pop = pop;
    }

    public Double getRain() {
        return rain;
    }

    public void setRain(Double rain) {
        this.rain = rain;
    }
}
