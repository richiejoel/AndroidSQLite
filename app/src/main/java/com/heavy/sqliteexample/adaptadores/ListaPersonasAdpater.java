package com.heavy.sqliteexample.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.heavy.sqliteexample.R;
import com.heavy.sqliteexample.entidades.Usuario;

import java.util.ArrayList;

public class ListaPersonasAdpater extends RecyclerView.Adapter<ListaPersonasAdpater.ViewHolderPersonas> {

    ArrayList<Usuario> listaUsuario;

    public ListaPersonasAdpater(ArrayList<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    @NonNull
    @Override
    public ViewHolderPersonas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_personas, null, false);
        return new ViewHolderPersonas(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPersonas holder, int position) {
        holder.documento.setText(listaUsuario.get(position).getId().toString());
        holder.nombre.setText(listaUsuario.get(position).getNombre());
        holder.telefono.setText(listaUsuario.get(position).getTelefono());
    }

    @Override
    public int getItemCount() {
        return this.listaUsuario.size();
    }

    public class ViewHolderPersonas extends RecyclerView.ViewHolder {
        TextView documento,nombre,telefono;
        public ViewHolderPersonas(@NonNull View itemView) {
            super(itemView);

            documento = itemView.findViewById(R.id.documentoRe);
            nombre = itemView.findViewById(R.id.nombreRe);
            telefono = itemView.findViewById(R.id.telefonoRe);

        }
    }
}
