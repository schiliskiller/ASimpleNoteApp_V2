package com.example.simplenotesapp_team9.entities;

import java.sql.Timestamp;

public record Notas (
        String titulo,
        String descripcion,
        Timestamp fecha_nota
) {}
