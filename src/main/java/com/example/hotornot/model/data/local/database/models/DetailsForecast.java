package com.example.hotornot.model.data.local.database.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.hotornot.model.data.local.database.converters.CityTypeConverter;
import com.example.hotornot.model.data.local.database.converters.DetailsWeatherItemTypeConverter;
import com.example.hotornot.model.data.local.database.converters.WeatherTypeConverter;
import com.example.hotornot.model.data.remote.models.DetailForecastResponse;

import java.util.List;

@Entity(tableName = "details_forecast")
public class DetailsForecast {

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
    private List<DetailWeatherItem> detailWeatherItem = null;

    public DetailsForecast() {
    }

    public DetailsForecast(DetailForecastResponse detailForecastResponse) {

        this.city = detailForecastResponse.getCity();
        this.message = detailForecastResponse.getMessage();
        this.cnt = detailForecastResponse.getCnt();
        this.detailWeatherItem = detailForecastResponse.getDetailWeatherItem();

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

    public List<DetailWeatherItem> getDetailWeatherItem() {
        return detailWeatherItem;
    }

    public void setDetailWeatherItem(List<DetailWeatherItem> detailWeatherItem) {
        this.detailWeatherItem = detailWeatherItem;
    }
}
