package com.example.hotornot.view.fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hotornot.R;
import com.example.hotornot.databinding.FragmentDetailsForecastTabBinding;
import com.example.hotornot.databinding.FragmentOverallForecastTabBinding;
import com.example.hotornot.model.data.local.database.models.CurrentWeather;
import com.example.hotornot.model.data.local.database.models.DetailsForecast;
import com.example.hotornot.model.data.local.database.services.CurrentWeatherService;
import com.example.hotornot.model.data.local.database.services.DetailsForecastService;
import com.example.hotornot.presenter.OverallForecastPresenter;

import org.joda.time.Hours;
import org.joda.time.Instant;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import java.time.ZoneId;
import java.util.Date;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

import static android.content.Context.LOCATION_SERVICE;

public class OverallForecastTabFragment extends Fragment implements OverallForecastPresenter.OverallForecastListener, LocationListener {

    private static final long LOCATION_REFRESH_TIME = 10000;
    private static final float LOCATION_REFRESH_DISTANCE = 1;
    private static final int REQUEST_LOCATION_PERMISSION = 1001;
    private OverallForecastPresenter presenter;
    private CurrentWeatherService service;
    private DetailsForecastService detailsForecastService;
    private FragmentOverallForecastTabBinding binding;
    private LocationManager mLocationManager;
    private Location location;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLocationManager = (LocationManager) getContext().getSystemService(LOCATION_SERVICE);
        location = new Location("");
        location.setLatitude(43.2031803);
        location.setLongitude(23.5602628);
        requestLocationPermission();

        presenter = new OverallForecastPresenter(this);
        service = new CurrentWeatherService(getContext());
        detailsForecastService = new DetailsForecastService(getContext());

    }

    private void getCurrentWeather() {
        service.getCurrentWeathers(new CurrentWeatherService.DataListener<CurrentWeather>() {
            @Override
            public void onData(CurrentWeather data) {
                if (data == null || isLocalDataOutdated(data)) {
                    presenter.getCurrentWeather(location.getLatitude(), location.getLongitude());
                } else {
                    setTodayCardInformation(data);
                }
            }
        });
    }

    private void getTomorrowForecast() {

    }

    private boolean isLocalDataOutdated(CurrentWeather currentWeather) {
        LocalDateTime date =
                LocalDateTime.fromDateFields(new Date(currentWeather.getRequestTime()));
        Hours hours = Hours.hoursBetween(date, LocalDateTime.now());

        return hours.getHours() > 3;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOverallForecastTabBinding.inflate(inflater, container, false);
        getCurrentWeather();
        return binding.getRoot();

    }

    @Override
    public void onCurrentWeatherReceived(CurrentWeather currentWeather) {
        service.addCurrentWeather(currentWeather);
        setTodayCardInformation(currentWeather);
    }

    @Override
    public void onNextDayForecastReceived(DetailsForecast detailsForecast) {
        detailsForecastService.addNextDayForecast(detailsForecast);
        setTomorrowCardInformation(detailsForecast);

    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void setTodayCardInformation(CurrentWeather currentWeather) {
        binding.txtDate.setText(String.valueOf(currentWeather.getCurrentDate()));
        binding.txtForecast.setText(currentWeather.getWeather().get(0).getMain());
        binding.txtCloud.setText(String.valueOf(currentWeather.getClouds().getAll())+ "%");
        binding.txtWind.setText(String.valueOf(currentWeather.getWind().getSpeed()) + "m/s");
        binding.txtHumidity.setText(String.valueOf(currentWeather.getMain().getHumidity()) + "%");
        binding.txtDegrees.setText(String.valueOf(currentWeather.getMain().getTemp().intValue()) + "°");
        binding.txtMinMaxDegrees.setText(currentWeather.getMain().getTempMin().intValue() + "°" + " / " + currentWeather.getMain().getTempMax().intValue() + "°");
        binding.txtDescription.setText(currentWeather.getWeather().get(0).getDescription());
    }

    public void setTomorrowCardInformation(DetailsForecast detailsForecast) {
//        binding.txtDateTomorrow.setText(detailsForecast.);
//        binding.txtForecastTomorrow.setText(detailsForecast.);
//        binding.txtCloudTomorrow.setText(detailsForecast.);
//        binding.txtWindTomorrow.setText(detailsForecast.);
//        binding.txtHumidity.setText(detailsForecast.);
//        binding.txtDegreesTomorrow.setText(detailsForecast.);
//        binding.txtMinMaxDegreesTomorrow.setText(detailsForecast.);
//        binding.txtDescriptionTomorrow.setText(detailsForecast.ь);
    }

    @SuppressLint("MissingPermission")
    @AfterPermissionGranted(REQUEST_LOCATION_PERMISSION)
    public void requestLocationPermission() {
        String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION};
        if (EasyPermissions.hasPermissions(getContext(), perms)) {
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_REFRESH_TIME,
                    LOCATION_REFRESH_DISTANCE, this);
        } else {
            EasyPermissions.requestPermissions(this, "Please grant the location permission", REQUEST_LOCATION_PERMISSION, perms);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


    @Override
    public void onLocationChanged(@NonNull Location location) {
        this.location = location;
    }
}