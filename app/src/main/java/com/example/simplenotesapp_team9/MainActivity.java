package com.example.simplenotesapp_team9;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.simplenotesapp_team9.databinding.ActivityMainBinding;
import com.example.simplenotesapp_team9.vmodel.NoteViewModel;

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

        EditText InputNota    = bind.textnota;     // entrada de la nota a crear
        TextView notascreadas = bind.notascreadas; // notas creadas
                 countnotas   = bind.countnotas;   // conteo de notas creadas
        Button   BotonGuardar = bind.BotonGuardar, // boton Guardar
                 BotonClear   = bind.BotonClear;   // boton Clear

        //cargar datos si ya habían antes
        loadPreferences();

        // aqui se muestra el conteo de las notas ya creadas y las notas
        countnotas.setText("Notas creadas: " + contador_notas);
        notascreadas.setText(viewmodel.notas_creadas);

        // al tocar el boton Guardar:
        BotonGuardar.setOnClickListener(view -> {
            // se guarda la nota
            String nota = InputNota.getText().toString();
            if(!nota.isEmpty())
            {
                guardarNota(nota, notascreadas);
                // vlver a mostrar el conteo notas
                countnotas.setText("Notas creadas: " + contador_notas);
                Toast.makeText(this, "Nota guardada :)", Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(this, "INGRESE UNA NOTA >:C", Toast.LENGTH_SHORT).show();

            // se notifica que la nota ha sido guardada
            InputNota.setText("");

        });

        // al tocar el boton Clear:
        BotonClear.setOnClickListener(view -> {
            // se borran todas las notas creadas
            bind.notascreadas.setText("");
            contador_notas = 0;
            // se notifica que las notas han sido eliminadas y actualiza el conteo
            countnotas.setText("Notas creadas: " + contador_notas);
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
            dest.setText(++contador_notas + ". " + nota);
        else
            dest.setText(dest.getText().toString() + "\n" + ++contador_notas + ". " + nota);

    }

    @Override
    protected void onStop () {
        super.onStop ();
        savePreferences ();
    }

    private void loadPreferences()
    {
        var save = getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);

        //cargar datos a viewmodel
        viewmodel.cont_notas = save.getInt(NOTES_COUNT,0);
        viewmodel.notas_creadas = save.getString(NOTES_CONTENIDO,"");
        contador_notas = viewmodel.cont_notas;
    }

    private void savePreferences()
    {
        var sharedPreferences = getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);
        var editor = sharedPreferences.edit();
        viewmodel.cont_notas = contador_notas;
        editor.putInt(NOTES_COUNT, viewmodel.cont_notas);
        editor.putString(NOTES_CONTENIDO, viewmodel.notas_creadas);
        editor.apply();
    }
}
