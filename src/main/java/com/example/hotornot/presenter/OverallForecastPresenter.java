package com.example.hotornot.presenter;

import com.example.hotornot.model.data.local.database.models.CurrentWeather;
import com.example.hotornot.model.data.remote.api.Api;
import com.example.hotornot.model.data.remote.api.CurrentWeatherResponse;
import com.example.hotornot.model.data.remote.api.DetailForecastResponse;
import com.example.hotornot.view.fragments.OverallForecastTabFragment;

public class OverallForecastPresenter {

    private OverallForecastListener listener;
    private Api api;

    public OverallForecastPresenter(OverallForecastListener listener) {
        this.listener = listener ;
        this.api = Api.getInstance();
    }

    public void getCurrentWeather(double lat, double lon) {
        api.getCurrentWeather(lat, lon, new Api.DataListener<CurrentWeather>() {
            @Override
            public void onDataReceived(CurrentWeather data) {
                listener.onCurrentWeatherReceived(data);
            }

            @Override
            public void onFailure(String message) {
                listener.onFailure(message);
            }
        });
    }

    public interface OverallForecastListener {
        void onCurrentWeatherReceived(CurrentWeather currentWeather);

        void onNextDayForecastReceived();

        void onFailure(String message);
    }
}
