package com.example.simplenotesapp_team9.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.simplenotesapp_team9.entities.NotasDB;

import java.util.List;

@Dao
public interface NotasDAO {
    @Insert
    void insertNota(NotasDB nota);
    @Query("SELECT * FROM notas")
    List<NotasDB> getNotas();
}
