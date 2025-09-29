package com.example.simplenotesapp_team9.db;

import androidx.room.TypeConverter;

import java.sql.Timestamp;

public class Converters {
    @TypeConverter
    public static Timestamp toTimestamp(Long value)
    {
        return value == null ? null : new Timestamp(value);
    }

    @TypeConverter
    public static Long timestampToTime(Timestamp ts)
    {
        return ts == null ? null : ts.getTime();
    }
}
