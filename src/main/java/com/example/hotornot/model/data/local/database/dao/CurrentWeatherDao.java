package com.example.hotornot.model.data.local.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.hotornot.model.data.local.database.models.CurrentWeather;

import java.util.List;

@Dao
public interface CurrentWeatherDao {

    @Query("SELECT * FROM current_weather")
    List<CurrentWeather> getAll();

    @Insert
    void insertAll(CurrentWeather... currentWeather);

    @Delete
    void delete(CurrentWeather currentWeather);

    @Query("SELECT * FROM current_weather LIMIT 1")
    CurrentWeather getCurrentWeather();
}
