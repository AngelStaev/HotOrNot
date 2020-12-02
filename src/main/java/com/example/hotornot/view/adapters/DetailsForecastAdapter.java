package com.example.hotornot.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotornot.R;
import com.example.hotornot.model.data.local.database.models.DetailsForecast;

import java.util.List;

public class DetailsForecastAdapter extends RecyclerView.Adapter<DetailsForecastAdapter.ViewHolder> {

    private List<DetailsForecast> detailsForecastList;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_details_forecast, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DetailsForecast detailsForecast = detailsForecastList.get(position);

//        holder.dailyDegrees.setText(detailsForecast.getDetailWeatherItem().getDeg());
//        holder.date.setText(detailsForecast.getDetailWeatherItem().getDtTxt());
//        holder.weather.setText(detailsForecast.getDetailWeatherItem().getWeather().);
//        holder.description.setText();
//        holder.forecastIcon.setImageResource();

    }

    @Override
    public int getItemCount() {
        return detailsForecastList.size();
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
