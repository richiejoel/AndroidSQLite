package com.heavy.sqliteexample;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.heavy.sqliteexample.entidades.Usuario;
import com.heavy.sqliteexample.utilidades.Utils;

import java.util.ArrayList;

public class ConsultarListViewActivity extends AppCompatActivity {

    ListView listViewUsuarios;
    ArrayList<String> listaPersonas;
    ArrayList<Usuario> listaUsuarios;
    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_list_view);

        conn = new ConexionSQLiteHelper(getApplicationContext(),"bd_usuarios",null,1);

        listViewUsuarios = findViewById(R.id.listViewUsuarios);

        mConsutarListaPersonas();

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaPersonas);
        listViewUsuarios.setAdapter(adapter);

        listViewUsuarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),listaUsuarios.get(position).getNombre().toString(),Toast.LENGTH_SHORT).show();
                mCrearDialogo(listaUsuarios.get(position).getNombre().toString(), listaUsuarios.get(position).getId(), listaUsuarios.get(position).getTelefono(), listaUsuarios.get(position));
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
        for(Usuario u: listaUsuarios){
            listaPersonas.add(u.getId().toString()+" - "+u.getNombre().toString());
        }
    }

    private void mCrearDialogo(String nombre, Integer documento, String telefono, final Usuario user){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(nombre);
        builder.setMessage("Documento: "+documento.toString() + "\n Teléfono: "+telefono);
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Hacer cosas aqui al hacer clic en el boton de aceptar
                Intent myIntent = new Intent(ConsultarListViewActivity.this, DetalleUsuarioActivity.class);
                Bundle myBundle = new Bundle();
                myBundle.putSerializable("usuario", user);
                myIntent.putExtras(myBundle);
                startActivity(myIntent);
            }
        });
        builder.show();
    }
}
