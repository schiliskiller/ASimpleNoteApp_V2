package com.example.simplenotesapp_team9;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.simplenotesapp_team9.databinding.AddNoteBinding;
import com.example.simplenotesapp_team9.db.AppDB;
import com.example.simplenotesapp_team9.entities.Notas;
import com.example.simplenotesapp_team9.entities.NotasDB;
import com.google.android.material.snackbar.Snackbar;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class AddNote extends AppCompatActivity {
    AddNoteBinding bind = AddNoteBinding.inflate(getLayoutInflater());
    AppDB db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bind.getRoot());
        db = Room
                .databaseBuilder(this, AppDB.class, "notas")
                .build();

        EditText notaTitle = bind.textTitulo;
        EditText notaCont  = bind.textCont;

        bind.BotonGuardar.setOnClickListener(v -> {
            if (!validateForm())
            {
                return;
            }

            Notas nota = new Notas(notaTitle.getText().toString(), notaCont.getText().toString(), new java.sql.Timestamp(new Date().getTime()));
            NotasDB notadb = new NotasDB();

            notadb.titulo = nota.titulo();
            notadb.descripcion = nota.descripcion();
            notadb.fecha_nota = new java.sql.Timestamp(nota.fecha_nota().getTime());
            NotasDB.idGlobal++;

            CompletableFuture.supplyAsync(() -> saveForm(notadb))
                    .thenAccept(opt -> {
                        boolean result = opt.orElse(false);
                        if (result)
                        {
                            Snackbar.make(bind.getRoot(), "Nota guardada", Snackbar.LENGTH_LONG).show();
                            clearFields();
                            Intent intent = new Intent(AddNote.this, MainActivity.class);
                            startActivity(intent);
                        }
                        else
                        {
                            Snackbar.make(bind.getRoot(), "Error al guardar nota", Snackbar.LENGTH_LONG).show();
                        }
                    });
        });
    }

    private boolean validateForm() {
        if (bind.textTitulo.getText().toString().isEmpty())
        {
            bind.textTitulo.setText("Titulo " + NotasDB.idGlobal);
            return true;
        }
        if (bind.textCont.getText().toString().isEmpty())
        {
            bind.textCont.setError("Descripcion requerida");
            return false;
        }

        return true;
    }

    protected Optional<Boolean> saveForm(NotasDB notas)
    {
        try {
            db.NotasDAO().insertNota(notas);
        } catch (Exception e) {
            return Optional.empty();
        }

        return Optional.of(true);
    }

    private void clearFields()
    {
        bind.textTitulo.setText("");
        bind.textCont.setText("");
    }
}
