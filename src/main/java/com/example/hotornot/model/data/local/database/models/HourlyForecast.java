package com.example.hotornot.model.data.local.database.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.hotornot.model.data.local.database.converters.CityTypeConverter;
import com.example.hotornot.model.data.local.database.converters.DateTypeConverter;
import com.example.hotornot.model.data.local.database.converters.DetailsWeatherItemTypeConverter;
import com.example.hotornot.model.data.remote.models.DetailForecastResponse;
import com.example.hotornot.model.data.remote.models.HourlyForecastResponse;

import org.joda.time.LocalDateTime;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Entity(tableName = "hourly_forecast")
public class HourlyForecast {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @TypeConverters(CityTypeConverter.class)
    @ColumnInfo(name = "city")
    private City city;

    @ColumnInfo(name = "message")
    private Double message;

    @ColumnInfo(name = "cnt")
    private Integer cnt;

    @TypeConverters(DetailsWeatherItemTypeConverter.class)
    @ColumnInfo(name = "list")
    private List<HourlyWeatherItem> hourlyWeatherItems = null;

    @TypeConverters(DateTypeConverter.class)
    @ColumnInfo(name = "created_on")
    private Date createdOn;

    public HourlyForecast() {
        createdOn = new Date();
    }

    public HourlyForecast(HourlyForecastResponse hourlyForecastResponse) {
        createdOn = new Date();
        this.city = hourlyForecastResponse.getCity();
        this.message = hourlyForecastResponse.getMessage();
        this.cnt = hourlyForecastResponse.getCnt();
        this.hourlyWeatherItems = hourlyForecastResponse.getHourlyWeatherItems();

    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Double getMessage() {
        return message;
    }

    public void setMessage(Double message) {
        this.message = message;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public List<HourlyWeatherItem> getHourlyWeatherItems() {
        return hourlyWeatherItems;
    }

    public void setHourlyWeatherItems(List<HourlyWeatherItem> hourlyWeatherItems) {
        this.hourlyWeatherItems = hourlyWeatherItems;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getTomorrowDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return simpleDateFormat.format(LocalDateTime.fromDateFields(createdOn).plusDays(1).toDate());
    }
}
