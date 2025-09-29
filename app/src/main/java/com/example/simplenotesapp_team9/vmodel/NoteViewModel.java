package com.example.simplenotesapp_team9.vmodel;

import androidx.lifecycle.ViewModel;

import com.example.simplenotesapp_team9.entities.Notas;
import com.example.simplenotesapp_team9.entities.NotasDB;

import java.util.List;

public class NoteViewModel extends ViewModel {
    public List<Notas> notas_creadas;
    public int cont_notas;

}
