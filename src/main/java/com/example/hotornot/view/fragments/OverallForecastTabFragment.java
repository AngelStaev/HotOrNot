package com.example.hotornot.view.fragments;

import android.location.LocationManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hotornot.R;
import com.example.hotornot.databinding.FragmentDetailsForecastTabBinding;
import com.example.hotornot.databinding.FragmentOverallForecastTabBinding;
import com.example.hotornot.model.data.local.database.models.CurrentWeather;
import com.example.hotornot.model.data.local.database.services.CurrentWeatherService;
import com.example.hotornot.presenter.OverallForecastPresenter;

import org.joda.time.Hours;
import org.joda.time.Instant;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import java.time.ZoneId;
import java.util.Date;

public class OverallForecastTabFragment extends Fragment implements OverallForecastPresenter.OverallForecastListener {

    private OverallForecastPresenter presenter;
    private CurrentWeatherService service;
    FragmentOverallForecastTabBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new OverallForecastPresenter(this);
        service = new CurrentWeatherService(getContext());
        getCurrentWeather();
    }

    private void getCurrentWeather() {
        service.getCurrentWeathers(new CurrentWeatherService.DataListener<CurrentWeather>() {
            @Override
            public void onData(CurrentWeather data) {
                if (data == null || isLocalDataOutdated(data)){
//                    presenter.getCurrentWeather();    set location
                } else {
//                    set recyclerView Data
                }
            }
        });
    }

    private boolean isLocalDataOutdated(CurrentWeather currentWeather) {
        LocalDateTime date =
                LocalDateTime.fromDateFields(new Date(currentWeather.getRequestTime()));
        Hours hours = Hours.hoursBetween(date, LocalDateTime.now());

        if (hours.getHours() > 3) {
            return true;
        }
        return false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOverallForecastTabBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onCurrentWeatherReceived(CurrentWeather currentWeather) {
        service.addCurrentWeather(currentWeather);
        //set views
    }

    @Override
    public void onNextDayForecastReceived() {

    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

}