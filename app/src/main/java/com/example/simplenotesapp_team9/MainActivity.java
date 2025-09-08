package com.example.simplenotesapp_team9;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.simplenotesapp_team9.databinding.ActivityMainBinding;
import com.example.simplenotesapp_team9.vmodel.NoteViewModel;

public class MainActivity extends AppCompatActivity {
    private NoteViewModel viewmodel;
    private ActivityMainBinding bind;
    private int contador_notas = 0;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewmodel = new ViewModelProvider(this).get(NoteViewModel.class);
        bind      = ActivityMainBinding.inflate(getLayoutInflater());

        EditText InputNota    = bind.textnota;     // entrada de la nota a crear
        TextView notascreadas = bind.notascreadas, // notas creadas
                 countnotas   = bind.countnotas;   // muestra el conteo de notas creadas
        Button   BotonGuardar = bind.BotonGuardar, // boton Guardar
                 BotonClear   = bind.BotonClear;   // boton Clear

        // aqui se muestra el conteo de las notas ya creadas
        countnotas.setText(countnotas.getText().toString() + " " + contador_notas);

        // al tocar el boton Guardar:
        BotonGuardar.setOnClickListener(view -> {
            // se guarda la nota
            guardarNota(InputNota.getText().toString(), notascreadas);
            // se notifica que la nota ha sido guardada
            Toast.makeText(this, "Nota guardada", Toast.LENGTH_SHORT).show();
        });

        // al tocar el boton Clear:
        BotonClear.setOnClickListener(view -> {
            // se borran todas las notas creadas
            bind.notascreadas.setText("");
            contador_notas = 0;
            // se notifica que las notas han sido eliminadas
            Toast.makeText(this, "Notas eliminadas", Toast.LENGTH_SHORT).show();
        });
    }

    // cuando la actividad esta en pausa, se almacenan las notas
    // y el contenido de la entrada de nota
    @Override
    protected void onPause() {
        super.onPause();

        viewmodel.nota_a_insertar = bind.textnota.getText().toString();
        viewmodel.notas_creadas   = bind.notascreadas.getText().toString();
    }

    // al resumir dicha actividad, se asignan los datos
    // anteriormente almacenados en onPause()
    @Override
    protected void onResume() {
        super.onResume();

        bind.textnota.setText(viewmodel.nota_a_insertar);
        bind.notascreadas.setText(viewmodel.notas_creadas);
    }

    // aqui almacena la nota a insertar, y por el momento se usan
    // como ID los numeros consecutivos extraidos del conteo de notas
    // y este va incrementando conforme se vayan agregando notas
    @SuppressLint("SetTextI18n")
    protected void guardarNota(String nota, TextView dest)
    {
        if (dest.getText().toString().isEmpty())
            dest.setText(++contador_notas + " " + nota);
        else
            dest.setText(dest.getText().toString() + "\n " + ++contador_notas + nota);
    }
}
