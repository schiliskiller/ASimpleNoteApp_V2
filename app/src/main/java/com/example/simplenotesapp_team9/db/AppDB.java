package com.example.simplenotesapp_team9.db;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.simplenotesapp_team9.dao.NotasDAO;
import com.example.simplenotesapp_team9.entities.Notas;

@Database(entities = {Notas.class}, version = 1)
public abstract class AppDB extends RoomDatabase {
    public abstract NotasDAO NotasDAO();

}
