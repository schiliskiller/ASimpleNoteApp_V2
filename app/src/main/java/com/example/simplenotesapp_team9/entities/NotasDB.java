package com.example.simplenotesapp_team9.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Timestamp;

@Entity(tableName = "notas")
public class NotasDB implements Comparable<NotasDB> {

    public static int idGlobal = 1;
    @PrimaryKey (autoGenerate = true)
    public int id;
    public String titulo;
    public String descripcion;
    public Timestamp fecha_nota;


    @Override
    public int compareTo(NotasDB o) {
        return Integer.compare(o.id, this.id);
    }
}
