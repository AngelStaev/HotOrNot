package com.example.hotornot.model.data.local.database.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "temps")
public class Temp {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "day")
    private Double day;

    @ColumnInfo(name = "min")
    private Double min;

    @ColumnInfo(name = "max")
    private Double max;

    @ColumnInfo(name = "night")
    private Double night;

    @ColumnInfo(name = "eve")
    private Double eve;

    @ColumnInfo(name = "morn")
    private Double morn;

    public Temp() {
    }

    public Temp(Double day, Double min, Double max, Double night, Double eve, Double morn) {
        this.day = day;
        this.min = min;
        this.max = max;
        this.night = night;
        this.eve = eve;
        this.morn = morn;
    }

    public Double getDay() {
        return day;
    }

    public void setDay(Double day) {
        this.day = day;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public Double getNight() {
        return night;
    }

    public void setNight(Double night) {
        this.night = night;
    }

    public Double getEve() {
        return eve;
    }

    public void setEve(Double eve) {
        this.eve = eve;
    }

    public Double getMorn() {
        return morn;
    }

    public void setMorn(Double morn) {
        this.morn = morn;
    }
}
