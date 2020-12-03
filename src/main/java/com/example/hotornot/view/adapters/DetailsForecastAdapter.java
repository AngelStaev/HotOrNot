package com.example.hotornot.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotornot.R;
import com.example.hotornot.model.data.local.WeatherType;
import com.example.hotornot.model.data.local.database.models.HourlyWeatherItem;

import java.util.ArrayList;
import java.util.List;

public class DetailsForecastAdapter extends RecyclerView.Adapter<DetailsForecastAdapter.ViewHolder> {

    private List<HourlyWeatherItem> hourlyWeatherItems;

    public DetailsForecastAdapter() {
        hourlyWeatherItems = new ArrayList<>();
    }

    public void setData(List<HourlyWeatherItem> detailWeatherItemList) {
        hourlyWeatherItems.clear();
        hourlyWeatherItems.addAll(detailWeatherItemList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_details_forecast, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HourlyWeatherItem detailWeatherItem = hourlyWeatherItems.get(position);

        holder.dailyDegrees.setText(detailWeatherItem.getMain().getTemp().intValue() + "°");
        holder.date.setText(detailWeatherItem.getDtTxt());
        holder.weather.setText(detailWeatherItem.getWeather().get(0).getMain());
        holder.description.setText(detailWeatherItem.getWeather().get(0).getDescription());
        holder.forecastIcon.setImageResource(getIcon(detailWeatherItem.getWeather().get(0).getId()));

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

    @Override
    public int getItemCount() {
        return hourlyWeatherItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView dailyDegrees;
        TextView date;
        TextView weather;
        TextView description;
        ImageView forecastIcon;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dailyDegrees = itemView.findViewById(R.id.txtDegrees);
            date = itemView.findViewById(R.id.txtDate);
            weather = itemView.findViewById(R.id.txtWeather);
            description = itemView.findViewById(R.id.txtDescription);
            forecastIcon = itemView.findViewById(R.id.imgForecast);

        }
    }
}
