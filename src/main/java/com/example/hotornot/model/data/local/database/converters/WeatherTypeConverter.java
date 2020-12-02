package com.example.hotornot.model.data.local.database.converters;

import androidx.room.TypeConverter;

import com.example.hotornot.model.data.local.database.models.Weather;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class WeatherTypeConverter {

    Gson gson = new Gson();

    @TypeConverter
    public List<Weather> stringToList(String data) {
        if (data == null) {
            return Collections.emptyList();
        } else {
            Type type = new TypeToken<List<Weather>>() {

            }.getType();
            return gson.fromJson(data, type);
        }
    }

    @TypeConverter
    public String listToString(List<Weather> data) {
        return gson.toJson(data);
    }
}
