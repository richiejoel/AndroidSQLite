package com.heavy.sqliteexample;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.heavy.sqliteexample.entidades.Usuario;
import com.heavy.sqliteexample.utilidades.Utils;

import java.util.ArrayList;

public class ConsultarComboActivity extends AppCompatActivity {

    Spinner spinnerUsuario;
    TextView txtDocumentoCombo, txtNombreCombo, txtTelefonoCombo;
    ArrayList<String> listaPersonas;
    ArrayList<Usuario> listaUsuarios;
    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_combo);

        conn = new ConexionSQLiteHelper(getApplicationContext(),"bd_usuarios",null,1);

        spinnerUsuario = findViewById(R.id.spinnerUsuario);
        txtDocumentoCombo = findViewById(R.id.txtDocumentoCombo);
        txtNombreCombo = findViewById(R.id.txtNombreCombo);
        txtTelefonoCombo = findViewById(R.id.txtTelefonoCombo);

        mConsutarListaPersonas();

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,listaPersonas);
        spinnerUsuario.setAdapter(adapter);

        spinnerUsuario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position != 0) {
                    txtDocumentoCombo.setText(listaUsuarios.get(position - 1).getId().toString());
                    txtNombreCombo.setText(listaUsuarios.get(position - 1).getNombre().toString());
                    txtTelefonoCombo.setText(listaUsuarios.get(position - 1).getTelefono().toString());
                } else {
                    txtDocumentoCombo.setText("");
                    txtNombreCombo.setText("");
                    txtTelefonoCombo.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


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
