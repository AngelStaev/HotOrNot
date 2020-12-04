package com.example.hotornot.model.data.local.database.converters;

import androidx.room.TypeConverter;

import com.example.hotornot.model.data.local.database.models.Clouds;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class CloudsTypeConverter {

    Gson gson = new Gson();

    @TypeConverter
    public Clouds stringToClouds(String data) {
        if (data == null) {
            return null;
        } else {
            Type type = new TypeToken<Clouds>() {

            }.getType();
            return gson.fromJson(data, type);
        }
    }

    @TypeConverter
    public String cloudsToString(Clouds data) {
        return gson.toJson(data);
    }
}
