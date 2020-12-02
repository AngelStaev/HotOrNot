package com.example.hotornot.model.data.local.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.hotornot.model.data.local.database.models.DetailsForecast;

import java.util.List;

@Dao
public interface DetailsForecastDao {

    @Query("SELECT * FROM details_forecast")
    List<DetailsForecast> getAll();

    @Insert
    void insertAll(DetailsForecast... detailsForecast);

    @Delete
    void delete(DetailsForecast detailsForecast);

    @Query("SELECT * FROM details_forecast LIMIT 1")
    DetailsForecast getDetailsForecast();
}
