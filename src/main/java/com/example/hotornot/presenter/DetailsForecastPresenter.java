package com.example.hotornot.presenter;

import com.example.hotornot.model.data.local.database.models.HourlyForecast;
import com.example.hotornot.model.data.remote.api.Api;

public class DetailsForecastPresenter {

    private HourlyForecastListener listener;
    private Api api;

    public DetailsForecastPresenter(HourlyForecastListener listener) {
        this.listener = listener;
        this.api = Api.getInstance();
    }

    public void getDetailForecast(double lat, double lon) {
        api.getHourlyForecast(lat, lon, new Api.DataListener<HourlyForecast>() {
            @Override
            public void onDataReceived(HourlyForecast data) {
                listener.onHourlyForecastReceived(data);
            }

            @Override
            public void onFailure(String message) {
                listener.onFailure(message);
            }
        });
    }


    public interface HourlyForecastListener {
        void onHourlyForecastReceived(HourlyForecast hourlyForecast);

        void onFailure(String message);
    }
}
