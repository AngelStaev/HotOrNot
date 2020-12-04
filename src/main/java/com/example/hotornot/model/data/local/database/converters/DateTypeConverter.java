package com.example.hotornot.model.data.local.database.converters;

import androidx.room.TypeConverter;

import java.util.Date;

public class DateTypeConverter {

    @TypeConverter
    public Date millisToDate(long millis) {
       return new Date(millis);
    }

    @TypeConverter
    public long dateToMillis(Date date) {
        return date.getTime();
    }
}
