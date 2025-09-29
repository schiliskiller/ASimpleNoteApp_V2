package com.example.simplenotesapp_team9.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simplenotesapp_team9.entities.Notas;
import com.example.simplenotesapp_team9.R;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder> {
    private List<Notas> listaNotas;
    private OnItemSelectListener listener;

    public CustomAdapter(List<Notas> ln, OnItemSelectListener lstnr)
    {
        this.listaNotas = ln;
        this.listener = lstnr;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater infl = LayoutInflater.from(parent.getContext());
        View view = infl.inflate(R.layout.list_notes, parent, false);
        return new CustomViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        Notas nota = listaNotas.get(position);
        holder.bind(nota);
    }

    @Override
    public int getItemCount() {
        return listaNotas == null ? 0 : listaNotas.size();
    }
}
