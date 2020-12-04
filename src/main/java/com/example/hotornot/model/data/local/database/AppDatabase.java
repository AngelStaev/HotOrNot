package com.example.hotornot.model.data.local.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.hotornot.model.data.local.database.dao.CurrentWeatherDao;
import com.example.hotornot.model.data.local.database.dao.DetailsForecastDao;
import com.example.hotornot.model.data.local.database.models.City;
import com.example.hotornot.model.data.local.database.models.Clouds;
import com.example.hotornot.model.data.local.database.models.CurrentWeather;
import com.example.hotornot.model.data.local.database.models.DetailsForecast;
import com.example.hotornot.model.data.local.database.models.Main;
import com.example.hotornot.model.data.local.database.models.Temp;
import com.example.hotornot.model.data.local.database.models.Weather;
import com.example.hotornot.model.data.local.database.models.Wind;

@Database(entities = {CurrentWeather.class,
        DetailsForecast.class, City.class, Clouds.class,
        Main.class, Temp.class, Weather.class, Wind.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract CurrentWeatherDao currentWeatherDao();
    public abstract DetailsForecastDao detailsForecastDao();


}
