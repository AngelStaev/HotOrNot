package com.example.hotornot.model.data.local.database.converters;

import androidx.room.TypeConverter;

import com.example.hotornot.model.data.local.database.models.Main;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class MainTypeConverter {

    Gson gson = new Gson();

    @TypeConverter
    public Main stringToMain(String data) {
        if (data == null) {
            return null;
        } else {
            Type type = new TypeToken<Main>() {

            }.getType();
            return gson.fromJson(data, type);
        }
    }

    @TypeConverter
    public String mainToString(Main data) {
        return gson.toJson(data);
    }
}
