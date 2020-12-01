package com.example.hotornot.model.data.local.database.services;

import android.content.Context;
import android.os.AsyncTask;

import com.example.hotornot.model.data.local.database.Database;
import com.example.hotornot.model.data.local.database.dao.CurrentWeatherDao;
import com.example.hotornot.model.data.local.database.models.CurrentWeather;

import java.util.List;


public class CurrentWeatherService {

    private final CurrentWeatherDao currentWeatherDao;

    public CurrentWeatherService(Context context) {
        currentWeatherDao = Database.getInstance(context).currentWeatherDao();
    }

    public void getAllCurrentWeathers(final DataListener<List<CurrentWeather>> dataListener) {
        new AsyncTask<Void, Void, List<CurrentWeather>>() {

            @Override
            protected List<CurrentWeather> doInBackground(Void... voids) {
                return currentWeatherDao.getAll();
            }

            @Override
            protected void onPostExecute(List<CurrentWeather> currentWeathers) {
                dataListener.onData(currentWeathers);
                super.onPostExecute(currentWeathers);
            }
        }.execute();
    }

    public void getCurrentWeathers(final DataListener<CurrentWeather> dataListener) {
        new AsyncTask<Void, Void, CurrentWeather>() {

            @Override
            protected CurrentWeather doInBackground(Void... voids) {
                return currentWeatherDao.getCurrentWeather();
            }

            @Override
            protected void onPostExecute(CurrentWeather currentWeathers) {
                dataListener.onData(currentWeathers);
                super.onPostExecute(currentWeathers);
            }
        }.execute();
    }


    public void addCurrentWeather(final CurrentWeather currentWeather) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                currentWeatherDao.insertAll(currentWeather);
                return null;
            }
        }.execute();
    }

    public interface DataListener<T> {
        void onData(T data);
    }
}
