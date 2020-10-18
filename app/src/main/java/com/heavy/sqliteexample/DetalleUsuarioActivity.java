package com.heavy.sqliteexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.heavy.sqliteexample.entidades.Usuario;

public class DetalleUsuarioActivity extends AppCompatActivity {

    TextView txtId, txtNombre, txtTelefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_usuario);

        txtId = findViewById(R.id.txtId);
        txtNombre = findViewById(R.id.txtNombre);
        txtTelefono = findViewById(R.id.txtTelefono);

        Bundle objectBundle = getIntent().getExtras();
        Usuario user = null;
        if(objectBundle != null){
            user = (Usuario) objectBundle.getSerializable("usuario");
            txtId.setText(user.getId().toString());
            txtNombre.setText(user.getNombre());
            txtTelefono.setText(user.getTelefono());
        }


    }
}
