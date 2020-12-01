package com.example.hotornot.model.data.local.database.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "mains")
public class Main {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "temp")
    private Double temp;

    @ColumnInfo(name = "temp_min")
    private Integer tempMin;

    @ColumnInfo(name = "temp_max")
    private Double tempMax;

    @ColumnInfo(name = "pressure")
    private Integer pressure;

    @ColumnInfo(name = "humidity")
    private Integer humidity;

    public Main() {
    }

    public Main(Double temp, Integer tempMin, Double tempMax, Integer pressure, Integer humidity) {
        this.temp = temp;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.pressure = pressure;
        this.humidity = humidity;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Integer getTempMin() {
        return tempMin;
    }

    public void setTempMin(Integer tempMin) {
        this.tempMin = tempMin;
    }

    public Double getTempMax() {
        return tempMax;
    }

    public void setTempMax(Double tempMax) {
        this.tempMax = tempMax;
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
}
