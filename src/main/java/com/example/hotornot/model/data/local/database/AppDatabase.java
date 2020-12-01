package com.example.hotornot.model.data.local.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.hotornot.model.data.local.database.dao.CurrentWeatherDao;
import com.example.hotornot.model.data.local.database.models.CurrentWeather;

@Database(entities = {CurrentWeather.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract CurrentWeatherDao currentWeatherDao();


}
