package com.example.simplenotesapp_team9.db;


import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.example.simplenotesapp_team9.dao.NotasDAO;
import com.example.simplenotesapp_team9.entities.Notas;
import com.example.simplenotesapp_team9.entities.NotasDB;

@Database(entities = {NotasDB.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDB extends RoomDatabase {
    public abstract NotasDAO NotasDAO();

}
