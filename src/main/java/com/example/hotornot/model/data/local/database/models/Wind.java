package com.example.hotornot.model.data.local.database.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "winds")
public class Wind {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "speed")
    private Double speed;

    @ColumnInfo(name = "deg")
    private Integer deg;

    public Wind() {
    }

    public Wind(Double speed) {
        this.speed = speed;
    }

    public Wind(Integer deg) {
        this.deg = deg;
    }

    public Wind(Double speed, Integer deg) {
        this.speed = speed;
        this.deg = deg;
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
}
