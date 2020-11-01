package com.heavy.sqliteexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnRegistro, btnConsulta, btnConsultaSppiner, btnConsultaListView, btnRegistroMascota, btnConsultaListaMascota, btnConsultaUsuarioRecycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRegistro = findViewById(R.id.btnRegistro);
        btnConsulta = findViewById(R.id.btnConsulta);
        btnConsultaSppiner = findViewById(R.id.btnConsultaSppiner);
        btnConsultaListView = findViewById(R.id.btnConsultaListView);
        btnRegistroMascota = findViewById(R.id.btnRegistroMascota);
        btnConsultaListaMascota = findViewById(R.id.btnConsultaListaMascota);
        btnConsultaUsuarioRecycler = findViewById(R.id.btnConsultaUsuarioRecycler);

        btnRegistro.setOnClickListener(this);
        btnConsulta.setOnClickListener(this);
        btnConsultaSppiner.setOnClickListener(this);
        btnConsultaListView.setOnClickListener(this);
        btnRegistroMascota.setOnClickListener(this);
        btnConsultaListaMascota.setOnClickListener(this);
        btnConsultaUsuarioRecycler.setOnClickListener(this);

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_usuarios",  null, 1);
    }

    @Override
    public void onClick(View v) {
        Intent myIntent = null;
        switch (v.getId()){
            case R.id.btnRegistro:
                myIntent = new Intent(MainActivity.this, RegistrarUsuarioActivity.class);
                break;
            case R.id.btnConsulta:
                myIntent = new Intent(MainActivity.this, ConsultarUsuariosActivity.class);
                break;
            case R.id.btnConsultaSppiner:
                myIntent = new Intent(MainActivity.this, ConsultarComboActivity.class);
                break;
            case R.id.btnConsultaListView:
                myIntent = new Intent(MainActivity.this, ConsultarListViewActivity.class);
                break;
            case R.id.btnRegistroMascota:
                myIntent = new Intent(MainActivity.this, RegistroMascotaActivity.class);
                break;
            case R.id.btnConsultaListaMascota:
                myIntent = new Intent(MainActivity.this, ConsultarMascotasActivity.class);
                break;
            case R.id.btnConsultaUsuarioRecycler:
                myIntent = new Intent(MainActivity.this, ConsularPersonasRecyclerActivity.class);
                break;
        }
        if(myIntent != null){
            startActivity(myIntent);
        }

    }
}
