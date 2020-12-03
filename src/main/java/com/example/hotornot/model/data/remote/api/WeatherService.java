package com.example.hotornot.model.data.remote.api;

import com.example.hotornot.model.data.remote.models.CurrentWeatherResponse;
import com.example.hotornot.model.data.remote.models.DetailForecastResponse;
import com.example.hotornot.model.data.remote.models.HourlyForecastResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {

    String BASE_URL = "http://api.openweathermap.org/data/2.5/";

    @GET("weather")
    Call<CurrentWeatherResponse> getCurrentWeather(
            @Query("lat") double lat,
            @Query("lon") double lon,
            @Query("APPID") String key,
            @Query("units") String units );

    @GET("forecast/daily")
    Call<DetailForecastResponse> getTomorrowsForecast(
            @Query("lat") double lat,
            @Query("lon") double lon,
            @Query("APPID") String key,
            @Query("cnt") int day,
            @Query("units") String units
    );

    @GET("forecast")
    Call<HourlyForecastResponse> getHourlyForecast(
            @Query("lat") double lat,
            @Query("lon") double lon,
            @Query("APPID") String key,
            @Query("cnt") int hours,
            @Query("units") String units
    );


}
