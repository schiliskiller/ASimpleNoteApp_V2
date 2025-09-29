package com.example.simplenotesapp_team9.recyclerview;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simplenotesapp_team9.entities.Notas;
import com.example.simplenotesapp_team9.R;

public class CustomViewHolder extends RecyclerView.ViewHolder {
    TextView notaTitle;
    TextView notaCont;

    public CustomViewHolder(@NonNull View itemView, OnItemSelectListener listener) {
        super(itemView);

        itemView.setOnClickListener(v -> listener.OnItemSelect(getBindingAdapterPosition()));

        this.notaTitle = itemView.findViewById(R.id.noteTitle);
        this.notaCont  = itemView.findViewById(R.id.noteCont);
    }

    public void bind(Notas n)
    {
        this.notaTitle.setText(n.titulo());
        this.notaCont.setText(n.descripcion());
    }
}
