package com.example.hotornot.view.fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.hotornot.R;
import com.example.hotornot.databinding.FragmentOverallForecastTabBinding;
import com.example.hotornot.model.data.local.WeatherType;
import com.example.hotornot.model.data.local.database.models.CurrentWeather;
import com.example.hotornot.model.data.local.database.models.DetailsForecast;
import com.example.hotornot.model.data.local.database.services.CurrentWeatherService;
import com.example.hotornot.model.data.local.database.services.DetailsForecastService;
import com.example.hotornot.presenter.OverallForecastPresenter;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import org.joda.time.Hours;
import org.joda.time.LocalDateTime;

import java.util.Date;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class OverallForecastTabFragment extends Fragment implements OverallForecastPresenter.OverallForecastListener {

    private static final long LOCATION_REFRESH_TIME = 1000;
    private static final long LOCATION_REFRESH_FASTEST = 1000;
    private static final int REQUEST_LOCATION_PERMISSION = 1001;
    private OverallForecastPresenter presenter;
    private CurrentWeatherService service;
    private DetailsForecastService detailsForecastService;
    private FragmentOverallForecastTabBinding binding;
    private Location location;
    private FusedLocationProviderClient mFusedLocationClient;
    private LocationCallback mLocationCallback;
    private LocationRequest mLocationRequest;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new OverallForecastPresenter(this);
        service = new CurrentWeatherService(getContext());
        detailsForecastService = new DetailsForecastService(getContext());

        initLocationClient();

        requestLocationPermission();

    }

    private void initLocationClient() {

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(LOCATION_REFRESH_TIME);
        mLocationRequest.setFastestInterval(LOCATION_REFRESH_FASTEST);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        location = new Location("");
        location.setLatitude(43.2031803);
        location.setLongitude(23.5602628);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getContext());
        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                location = locationResult.getLastLocation();

                getCurrentWeather();
                getTomorrowForecast();


                mFusedLocationClient.removeLocationUpdates(mLocationCallback);
            }
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOverallForecastTabBinding.inflate(inflater, container, false);

        return binding.getRoot();

    }

    private void getCurrentWeather() {
        service.getCurrentWeathers(data -> {
            if (data == null || isLocalDataOutdated(data.getCreatedOn())) {
                presenter.getCurrentWeather(location.getLatitude(), location.getLongitude());
            } else {
                setTodayCardInformation(data);
            }
        });
    }

    private void getTomorrowForecast() {
        detailsForecastService.getNextDayForecast(data -> {
            if (data == null || isLocalDataOutdated(data.getCreatedOn())) {
                presenter.getNextDayForecast(location.getLatitude(), location.getLongitude());
            } else {
                setTomorrowCardInformation(data);
            }
        });

    }

    private boolean isLocalDataOutdated(Date weatherDate) {
        LocalDateTime date =
                LocalDateTime.fromDateFields(weatherDate);
        Hours hours = Hours.hoursBetween(date, LocalDateTime.now());

        return hours.getHours() > 3;
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
        binding.txtCloud.setText(currentWeather.getClouds().getAll() + "%");
        binding.txtWind.setText(currentWeather.getWind().getSpeed() + "m/s");
        binding.txtHumidity.setText(currentWeather.getMain().getHumidity() + "%");
        binding.txtDegrees.setText(currentWeather.getMain().getTemp().intValue() + "°");
        binding.txtMinMaxDegrees.setText(currentWeather.getMain().getTempMin().intValue() + "°"
                + " / " + currentWeather.getMain().getTempMax().intValue() + "°");
        binding.txtDescription.setText(currentWeather.getWeather().get(0).getDescription());
        binding.imgForecast.setImageResource(getIcon(currentWeather.getWeather().get(0).getId()));
        setCardBackground(currentWeather.getMain().getTemp().intValue(), binding.layoutToday);
    }

    public int getIcon(int weatherId) {
        if (weatherId != WeatherType.CLEAR) {
            weatherId = weatherId / 100;
        }
        switch (weatherId) {
            case WeatherType.CLEAR:
                return R.drawable.ic_wi_day_sunny;
            case WeatherType.CLOUDS:
                return R.drawable.ic_wi_cloudy;
            case WeatherType.DRIZZLE:
                return R.drawable.ic_wi_sleet;
            case WeatherType.EXTREME:
                return R.drawable.ic_wi_meteor;
            case WeatherType.FOG:
                return R.drawable.ic_wi_fog;
            case WeatherType.RAIN:
                return R.drawable.ic_wi_rain;
            case WeatherType.SNOW:
                return R.drawable.ic_wi_snow;
            case WeatherType.THUNDERSTORM:
                return R.drawable.ic_wi_lightning;
        }
        return R.drawable.ic_wi_meteor;
    }

    public void setTomorrowCardInformation(DetailsForecast detailsForecast) {
        binding.txtDateTomorrow.setText(detailsForecast.getTomorrowDate());
        binding.txtForecastTomorrow.setText(detailsForecast.getDetailWeatherItem().get(0).getWeather().get(0).getMain());
        binding.txtCloudTomorrow.setText(detailsForecast.getDetailWeatherItem().get(0).getClouds() + "%");
        binding.txtWindTomorrow.setText(detailsForecast.getDetailWeatherItem().get(0).getSpeed() + "m/s");
        binding.txtHumidity.setText(detailsForecast.getDetailWeatherItem().get(0).getHumidity() + "%");
        binding.txtDegreesTomorrow.setText(detailsForecast.getDetailWeatherItem().get(0).getTemp().getDay().intValue() + "°");
        binding.txtMinMaxDegreesTomorrow.setText(detailsForecast.getDetailWeatherItem()
                .get(0).getTemp().getMin().intValue() + "°" + " / "
                + detailsForecast.getDetailWeatherItem().get(0).getTemp().getMax().intValue() + "°");
        binding.txtDescriptionTomorrow.setText(detailsForecast.getDetailWeatherItem().get(0).getWeather().get(0).getDescription());
        binding.imgForecastTomorrow.setImageResource(getIcon(detailsForecast.getDetailWeatherItem().get(0)
                .getWeather().get(0).getId()));
        setCardBackground(detailsForecast.getDetailWeatherItem().get(0).getTemp().getDay().intValue()
                ,binding.layoutTomorrow);
    }

    @SuppressLint("MissingPermission")
    @AfterPermissionGranted(REQUEST_LOCATION_PERMISSION)
    public void requestLocationPermission() {
        String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION};
        if (EasyPermissions.hasPermissions(getContext(), perms)) {
            mFusedLocationClient.requestLocationUpdates(mLocationRequest,
                    mLocationCallback, Looper.myLooper());
        } else {
            EasyPermissions.requestPermissions(this, "Please grant the location permission", REQUEST_LOCATION_PERMISSION, perms);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


    public void setCardBackground(int temp, RelativeLayout layout) {
        if (temp > 22) {
            layout.setBackgroundColor(getContext().getResources().getColor(R.color.colorYellow));
        } else if (temp < 22 && temp > 15) {
            layout.setBackgroundColor(getContext().getResources().getColor(R.color.colorGreen));
        }
    }


}