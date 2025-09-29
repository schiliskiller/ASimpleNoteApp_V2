package com.example.simplenotesapp_team9;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.simplenotesapp_team9.databinding.ActivityMainBinding;
import com.example.simplenotesapp_team9.entities.Notas;
import com.example.simplenotesapp_team9.recyclerview.CustomAdapter;
import com.example.simplenotesapp_team9.vmodel.NoteViewModel;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private NoteViewModel viewmodel;
    final String   PREFERENCES_NAME = "A&D Notes";
    final String   NOTES_COUNT = "Num_Notas";
    final String    NOTES_CONTENIDO = "Notas_creadas";
    private ActivityMainBinding bind;
    private int contador_notas = 0;
    private TextView countnotas; // conteo de notas creadas

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // utilizando viewmodel y binding
        bind      = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
        viewmodel = new ViewModelProvider(this).get(NoteViewModel.class);

        Toolbar toolbar = (Toolbar) bind.toolbar;

        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        bind.RV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        bind.RV.setAdapter(new CustomAdapter(viewmodel.notas_creadas, pos -> {
            Notas nota = viewmodel.notas_creadas.get(pos);
            Snackbar.make(bind.RV, nota.titulo(), Snackbar.LENGTH_LONG).show();
        }));

        countnotas = bind.countnotas;   // conteo de notas creadas

        // aqui se muestra el conteo de las notas ya creadas y las notas
        countnotas.setText("Notas creadas: " + contador_notas);
    }

    protected void openAddNote()
    {
        Intent intent = new Intent(MainActivity.this, AddNote.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuAdd)
        {
            openAddNote();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // cuando la actividad esta en pausa, se almacenan las notas
    // y el contenido de la entrada de nota
    @Override
    protected void onPause() {
        super.onPause();

    }

    // al resumir dicha actividad, se asignan los datos
    // anteriormente almacenados en onPause()
    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onStop () {
        super.onStop ();
    }


}
