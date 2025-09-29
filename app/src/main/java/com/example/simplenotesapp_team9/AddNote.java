package com.example.simplenotesapp_team9;

import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.simplenotesapp_team9.databinding.AddNoteBinding;
import com.example.simplenotesapp_team9.entities.Notas;

import java.util.concurrent.CompletableFuture;

public class AddNote extends AppCompatActivity {
    AddNoteBinding bind = AddNoteBinding.inflate(getLayoutInflater());

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bind.getRoot());

        EditText notaTitle = bind.textTitulo;
        EditText notaCont  = bind.textCont;

        bind.BotonGuardar.setOnClickListener(v -> {
            Notas nota = new Notas(notaTitle.getText().toString(), notaCont.getText().toString());

            CompletableFuture.supplyAsync(() -> )
        });
    }
}
