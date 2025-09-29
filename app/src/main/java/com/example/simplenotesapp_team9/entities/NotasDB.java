package com.example.simplenotesapp_team9.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "nota")
public class NotasDB implements Comparable<NotasDB> {
    @PrimaryKey (autoGenerate = true)
    int id;
    String titulo;
    String descripcion;

    @Override
    public int compareTo(NotasDB o) {
        return Integer.compare(o.id, this.id);
    }
}
