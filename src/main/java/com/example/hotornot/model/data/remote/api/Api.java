package com.example.hotornot.model.data.remote.api;

import com.example.hotornot.model.data.local.Constants;
import com.example.hotornot.model.data.local.database.models.CurrentWeather;
import com.example.hotornot.model.data.local.database.models.DetailsForecast;
import com.example.hotornot.model.data.remote.models.CurrentWeatherResponse;
import com.example.hotornot.model.data.remote.models.DetailForecastResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

    private static Api instance;
    private final WeatherService service;

    public static Api getInstance() {
        if (instance == null) {
            instance = new Api();
        }
        return instance;
    }

    private Api() {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WeatherService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        service = retrofit.create(WeatherService.class);
    }

    public void getCurrentWeather(double lat, double lon, final DataListener<CurrentWeather> listener) {
        service.getCurrentWeather(lat, lon, Constants.API_KEY, Constants.UNITS).enqueue(new Callback<CurrentWeatherResponse>() {
            @Override
            public void onResponse(Call<CurrentWeatherResponse> call, Response<CurrentWeatherResponse> response) {
                if (response.isSuccessful()) {
                    CurrentWeather currentWeather = new CurrentWeather(response.body());
                    listener.onDataReceived(currentWeather);
                } else {
                    listener.onFailure(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<CurrentWeatherResponse> call, Throwable t) {
                listener.onFailure(t.getLocalizedMessage());
            }
        });

    }


    public void getTomorrowsForecast(double lat, double lon, final DataListener<DetailsForecast> listener) {
        service.getTomorrowsForecast(lat, lon, Constants.API_KEY, Constants.NEXT_DAY_FORECAST_PARAMETER, Constants.UNITS).enqueue(new Callback<DetailForecastResponse>() {
            @Override
            public void onResponse(Call<DetailForecastResponse> call, Response<DetailForecastResponse> response) {
                DetailsForecast detailsForecast = new DetailsForecast(response.body());
                listener.onDataReceived(detailsForecast);
            }

            @Override
            public void onFailure(Call<DetailForecastResponse> call, Throwable t) {
                listener.onFailure(t.getLocalizedMessage());
            }
        });

    }

    public void getHourlyForecast(final double lat, double lon, final DataListener<DetailsForecast> listener) {
        service.getHourlyForecast(lat, lon, Constants.API_KEY, Constants.HOURLY_FORECAST_PARAMETER, Constants.UNITS).enqueue(new Callback<DetailForecastResponse>() {
            @Override
            public void onResponse(Call<DetailForecastResponse> call, Response<DetailForecastResponse> response) {
                DetailsForecast detailsForecast = new DetailsForecast(response.body());
                listener.onDataReceived(detailsForecast);
            }

            @Override
            public void onFailure(Call<DetailForecastResponse> call, Throwable t) {
                listener.onFailure(t.getLocalizedMessage());
            }
        });
    }

    public interface DataListener<T> {
        void onDataReceived(T data);

        void onFailure(String message);
    }
}
