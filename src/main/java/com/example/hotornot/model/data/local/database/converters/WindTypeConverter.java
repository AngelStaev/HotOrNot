package com.example.hotornot.model.data.local.database.converters;

import androidx.room.TypeConverter;

import com.example.hotornot.model.data.local.database.models.Wind;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class WindTypeConverter {

    Gson gson = new Gson();

    @TypeConverter
    public Wind stringToWind(String data) {
        if (data == null) {
            return null;
        } else {
            Type type = new TypeToken<Wind>() {

            }.getType();
            return gson.fromJson(data, type);
        }
    }

    @TypeConverter
    public String windToString(Wind data) {
        return gson.toJson(data);
    }
}
