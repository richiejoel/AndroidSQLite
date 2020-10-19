package com.heavy.sqliteexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.heavy.sqliteexample.entidades.Usuario;
import com.heavy.sqliteexample.utilidades.Utils;

import java.util.ArrayList;

public class RegistroMascotaActivity extends AppCompatActivity implements View.OnClickListener {

    EditText txtNombreMascota, txtRazaMascota;
    Spinner spinnerDuenio;
    Button btnRegistrarMascota;
    ArrayList<String> listaPersonas;
    ArrayList<Usuario> listaUsuarios;
    ConexionSQLiteHelper conn;
    Integer duenioIdCommand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_mascota);

        conn = new ConexionSQLiteHelper(this, "bd_usuarios",  null, 1);

        txtNombreMascota = findViewById(R.id.txtNombreMascota);
        txtRazaMascota = findViewById(R.id.txtRazaMascota);
        spinnerDuenio = findViewById(R.id.spinnerDuenio);
        btnRegistrarMascota = findViewById(R.id.btnRegistrarMascota);

        btnRegistrarMascota.setOnClickListener(this);

        mConsutarListaPersonas();

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,listaPersonas);
        spinnerDuenio.setAdapter(adapter);

        spinnerDuenio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        mRegistrarMascotas();
    }

    private void mRegistrarMascotas() {
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_usuarios",  null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Utils.NOMBRE_MASCOTA, txtNombreMascota.getText().toString());
        values.put(Utils.RAZA_MASCOTA, txtRazaMascota.getText().toString());

        duenioIdCommand = (int) spinnerDuenio.getSelectedItemId();
        if(duenioIdCommand != 0){
            int idDuenio=listaUsuarios.get(duenioIdCommand-1).getId();
            values.put(Utils.ID_DUENIO, idDuenio);
            Long idResultante = db.insert(Utils.TABLA_MASCOTA, Utils.ID_MASCOTA, values);

            Toast.makeText(getApplicationContext(), "Id Registro "+idResultante,Toast.LENGTH_LONG).show();
            db.close();

        } else {
            Toast.makeText(getApplicationContext(), "Seleccione un dueño valido",Toast.LENGTH_LONG).show();
        }


    }

    private void mConsutarListaPersonas(){
        SQLiteDatabase db = conn.getReadableDatabase();
        Usuario usuario = null;
        listaUsuarios = new ArrayList<Usuario>();

        Cursor cursor = db.rawQuery("SELECT * FROM "+ Utils.TABLA_USUARIO+" ", null);

        while (cursor.moveToNext()){
            usuario = new Usuario();
            usuario.setId(cursor.getInt(0));
            usuario.setNombre(cursor.getString(1));
            usuario.setTelefono(cursor.getString(2));
            listaUsuarios.add(usuario);
        }
        mObtenerLista();

    }

    private void mObtenerLista(){
        listaPersonas = new ArrayList<String>();
        listaPersonas.add("Seleccione:");
        for(Usuario u: listaUsuarios){
            listaPersonas.add(u.getId().toString()+" - "+u.getNombre().toString());
        }
    }
}
