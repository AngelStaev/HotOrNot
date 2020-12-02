package com.example.hotornot.model.data.local.database.services;

import android.content.Context;
import android.os.AsyncTask;

import com.example.hotornot.model.data.local.database.Database;
import com.example.hotornot.model.data.local.database.dao.CurrentWeatherDao;
import com.example.hotornot.model.data.local.database.dao.DetailsForecastDao;
import com.example.hotornot.model.data.local.database.models.CurrentWeather;
import com.example.hotornot.model.data.local.database.models.DetailsForecast;

import java.util.List;

public class DetailsForecastService {

    private final DetailsForecastDao detailsForecastDao;


    public DetailsForecastService(Context context) {
        detailsForecastDao = Database.getInstance(context).detailsForecastDao();
    }

    public void getAllNextDayForecasts(final DetailsForecastService.DataListener<List<DetailsForecast>> dataListener) {
        new AsyncTask<Void, Void, List<DetailsForecast>>() {

            @Override
            protected List<DetailsForecast> doInBackground(Void... voids) {
                return detailsForecastDao.getAll();
            }

            @Override
            protected void onPostExecute(List<DetailsForecast> detailsForecasts) {
                dataListener.onData(detailsForecasts);
                super.onPostExecute(detailsForecasts);
            }
        }.execute();
    }

    public void getNextDayForecast(final DetailsForecastService.DataListener<DetailsForecast> dataListener) {
        new AsyncTask<Void, Void, DetailsForecast>() {

            @Override
            protected DetailsForecast doInBackground(Void... voids) {
                return detailsForecastDao.getDetailsForecast();
            }

            @Override
            protected void onPostExecute(DetailsForecast detailsForecast) {
                dataListener.onData(detailsForecast);
                super.onPostExecute(detailsForecast);
            }
        }.execute();
    }


    public void addNextDayForecast(final DetailsForecast detailsForecast) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                detailsForecastDao.insertAll(detailsForecast);
                return null;
            }
        }.execute();
    }

    public interface DataListener<T> {
        void onData(T data);
    }
}
